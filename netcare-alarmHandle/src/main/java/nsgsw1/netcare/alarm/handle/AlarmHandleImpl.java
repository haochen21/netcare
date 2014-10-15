package nsgsw1.netcare.alarm.handle;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import nsgsw1.netcare.alarm.cache.AlarmKnowledgeCache;
import nsgsw1.netcare.alarm.consumer.SendCurrAlarmConsumer;
import nsgsw1.netcare.alarm.util.DeviceNameUtil;
import nsgsw1.netcare.model.alarm.AlarmEvent;
import nsgsw1.netcare.model.alarm.AlarmKnowledge;
import nsgsw1.netcare.model.alarm.ClearInfo;
import nsgsw1.netcare.model.alarm.CurrAlarm;
import nsgsw1.netcare.model.alarm.constant.AlarmClearType;
import nsgsw1.netcare.model.alarm.constant.AlarmEventStatus;
import nsgsw1.netcare.model.alarm.constant.AlarmObjectType;
import nsgsw1.netcare.model.alarm.constant.AlarmOperation;
import nsgsw1.netcare.model.circuit.Circuit;
import nsgsw1.netcare.model.customer.Customer;
import nsgsw1.netcare.model.customer.CustomerGroup;
import nsgsw1.netcare.model.res.Card;
import nsgsw1.netcare.model.res.Ctp;
import nsgsw1.netcare.model.res.Me;
import nsgsw1.netcare.model.res.Port;
import nsgsw1.netcare.model.res.Shelf;
import nsgsw1.netcare.model.res.Slot;
import nsgsw1.netcare.model.res.constant.DNConst;
import nsgsw1.netcare.model.res.constant.MeDeviceType;
import nsgsw1.netcare.model.security.User;
import nsgsw1.netcare.service.AlarmService;
import nsgsw1.netcare.service.CircuitService;
import nsgsw1.netcare.service.CustomerService;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AlarmHandleImpl implements AlarmHandle, Callable<CurrAlarm> {

	@Autowired
	protected CircuitService circuitService;

	@Autowired
	protected AlarmService alarmService;

	@Autowired
	protected CustomerService customerService;

	@Autowired
	private AlarmKnowledgeCache alarmKnowledgeCache;

	@Autowired
	private SendCurrAlarmConsumer sendCurrAlarmConsumer;

	protected AlarmEvent alarmEvent;

	protected boolean preProcessOk = true;

	protected Me me;

	protected Shelf shelf;

	protected Slot slot;

	protected Card card;

	protected Port port;

	protected Ctp ctp;

	protected CurrAlarm currAlarm;

	protected Collection<Circuit> sncCircuits = new HashSet<>();

	protected Collection<Circuit> channelCircuits = new HashSet<>();

	protected Collection<Customer> customers = new HashSet<>();

	protected Collection<CustomerGroup> customerGroups = new HashSet<>();

	private final static Logger logger = LoggerFactory
			.getLogger(AlarmHandleImpl.class);

	public AlarmHandleImpl() {

	}

	public AlarmEvent getAlarmEvent() {
		return alarmEvent;
	}

	public void setAlarmEvent(AlarmEvent alarmEvent) {
		this.alarmEvent = alarmEvent;
	}

	@Override
	public void preProcess() {
		currAlarm = alarmService.findCurrAlarmByUid(alarmEvent.getUid());
		if (currAlarm != null
				&& isAlarmOld(alarmEvent.getMeCreateTime(),
						currAlarm.getMeUpdateTime()) == false) {
			preProcessOk = false;
			logger.info("to ignore the event: " + alarmEvent.getUid()
					+ ". AlarmEvent me time :" + alarmEvent.getMeCreateTime()
					+ ", currAlarm me update time:"
					+ currAlarm.getMeUpdateTime());
		}
		// 清除告警,直接清除
		if (alarmEvent.getStatus() == AlarmEventStatus.CLEARED
				&& currAlarm != null) {
			currAlarm.setClearTime(new Date());
			currAlarm.setEmsClearTime(alarmEvent.getEmsCreateTime());
			currAlarm.setMeClearTime(alarmEvent.getMeCreateTime());
			currAlarm.setOperation(AlarmOperation.DELETE);

			ClearInfo clearInfo = new ClearInfo();
			if (alarmEvent.getClearType() == null) {
				clearInfo.setClearType(AlarmClearType.EMS);
			} else {
				clearInfo.setClearType(alarmEvent.getClearType());
			}

			User user = new User();
			user.setName("system");
			clearInfo.setClearer(user);

			if (currAlarm.getSncCircuitIds().size() > 0) {
				sncCircuits = circuitService.findCircuitByIds(currAlarm
						.getSncCircuitIds());
				sncCircuits.stream().forEach(circuit -> {
					if (circuit.getUserIds().size() > 0) {
						currAlarm.getUserIds().addAll(circuit.getUserIds());
					}
				});
				currAlarm.setSncCircuits(sncCircuits);
			}
			if (currAlarm.getChannelCircuitIds().size() > 0) {
				channelCircuits = circuitService.findCircuitByIds(currAlarm
						.getChannelCircuitIds());
				channelCircuits.stream().forEach(circuit -> {
					if (circuit.getUserIds().size() > 0) {
						currAlarm.getUserIds().addAll(circuit.getUserIds());
					}
				});
				currAlarm.setChannelCircuits(channelCircuits);
			}
			if (currAlarm.getCustomerGroupIds().size() > 0) {
				customerGroups = customerService
						.findCustomerGroupByIds(currAlarm.getCustomerGroupIds());
				currAlarm.setCustomerGroups(customerGroups);
			}
			if (currAlarm.getCustomerIds().size() > 0) {
				customers = customerService.findCustomerByIds(currAlarm
						.getCustomerIds());
				currAlarm.setCustomers(customers);
			}
			currAlarm = alarmService.clearCurrAlarm(clearInfo, currAlarm);
			preProcessOk = false;
			logger.info("clear currAlarm.id is: " + currAlarm.getId()
					+ " successfully,operation is:" + currAlarm.getOperation());
		}
	}

	@Override
	public void doProcess() {
		if (preProcessOk == false)
			return;
		if (alarmEvent.getStatus() == AlarmEventStatus.ACTIVE) {
			if (currAlarm == null) {
				createNewCurrAlarm();
				currAlarm.setOperation(AlarmOperation.NEW);
			} else if (alarmEvent.getStatus() == AlarmEventStatus.ACTIVE) {
				currAlarm.setUpdateTime(new Date());
				currAlarm.setEmsUpdateTime(alarmEvent.getEmsCreateTime());
				currAlarm.setMeUpdateTime(alarmEvent.getMeCreateTime());
				currAlarm.setOperation(AlarmOperation.UPDATE);
			}
			makeUpCurrAlarm();
			currAlarm = alarmService.saveCurrAlarm(currAlarm);
			logger.info("create currAlarm.id is: " + currAlarm.getId()
					+ " successfully.");
		}
	}

	@Override
	public void postProcess() {
		if (currAlarm != null) {
			sendCurrAlarmConsumer.addCurrAlarm(currAlarm);
		}
	}

	@Override
	public CurrAlarm call() throws Exception {
		preProcess();
		doProcess();
		postProcess();
		return currAlarm;
	}

	protected void setCustomers() {
		Collection<ObjectId> ids = new HashSet<>();
		for (Circuit circuit : sncCircuits) {
			if (circuit.getaCustomerId() != null) {
				ids.add(circuit.getaCustomerId());
			}
			if (circuit.getzCustomerId() != null) {
				ids.add(circuit.getzCustomerId());
			}
		}
		for (Circuit circuit : channelCircuits) {
			if (circuit.getaCustomerId() != null) {
				ids.add(circuit.getaCustomerId());
			}
			if (circuit.getzCustomerId() != null) {
				ids.add(circuit.getzCustomerId());
			}
		}
		if (ids.size() > 0) {
			List<Customer> queryCustomers = customerService
					.findCustomerByIds(ids);
			customers.addAll(queryCustomers);
		}
	}

	protected void setCustomerGroups() {
		Collection<ObjectId> ids = new HashSet<>();
		for (Customer customer : customers) {
			if (customer.getCustomerGroupIds() != null
					&& customer.getCustomerGroupIds().size() > 0) {
				ids.addAll(customer.getCustomerGroupIds());
			}
		}
		if (ids.size() > 0) {
			List<CustomerGroup> queryCustomerGroups = customerService
					.findCustomerGroupByIds(ids);
			customerGroups.addAll(queryCustomerGroups);
		}
	}

	private void createNewCurrAlarm() {
		currAlarm = new CurrAlarm();
		currAlarm.setName(alarmEvent.getName());
		currAlarm.setSeverity(alarmEvent.getSeverity());
		currAlarm.setAck(false);
		currAlarm.setCategory(alarmEvent.getCategory());
		currAlarm.setType(alarmEvent.getType());
		currAlarm.setObjectType(alarmEvent.getObjectType());
		currAlarm.setUid(alarmEvent.getUid());
		currAlarm.setDeviceUid(alarmEvent.getDeviceUid());
		currAlarm.setCreateTime(new Date());
		currAlarm.setUpdateTime(new Date());
		currAlarm.setEmsCreateTime(alarmEvent.getEmsCreateTime());
		currAlarm.setEmsUpdateTime(alarmEvent.getEmsCreateTime());
		currAlarm.setMeCreateTime(alarmEvent.getMeCreateTime());
		currAlarm.setMeUpdateTime(alarmEvent.getMeCreateTime());
		currAlarm.setDeviceName(getDeviceName());
		currAlarm.setMe(me);
		currAlarm.setShelf(shelf);
		currAlarm.setSlot(slot);
		currAlarm.setCard(card);
		currAlarm.setPort(port);
		currAlarm.setCtp(ctp);
		currAlarm.setDescription(alarmEvent.getCause());
		currAlarm.setAdditionalInfo(alarmEvent.getAdditionalInfo());
	}

	private void makeUpCurrAlarm() {
		currAlarm.setAlarmKnowledge(getAlarmKnowledge());

		if (sncCircuits.size() > 0) {
			currAlarm.setBusiness(getIsBusiness(currAlarm.getAlarmKnowledge()));
		} else {
			currAlarm.setBusiness(false);
		}

		currAlarm.setSncCircuits(sncCircuits);
		currAlarm.setChannelCircuits(channelCircuits);
		currAlarm.setCustomerGroups(customerGroups);
		currAlarm.setCustomers(customers);

		if (currAlarm.getCustomers().size() > 0) {
			currAlarm.setCustomerInfo(getCustomerInfo());
			Set<ObjectId> customerIds = currAlarm.getCustomers().stream()
					.map(customer -> {
						return customer.getId();
					}).collect(Collectors.toSet());
			currAlarm.setCustomerIds(customerIds);
		} else {
			currAlarm.setCustomerInfo("");
			currAlarm.setCustomerIds(null);
		}

		if (currAlarm.getCustomerGroups().size() > 0) {
			currAlarm.setCustomerGroupInfo(getCustomerGroupInfo());
			Set<ObjectId> customerGroupIds = currAlarm.getCustomerGroups()
					.stream().map(customerGroup -> {
						return customerGroup.getId();
					}).collect(Collectors.toSet());
			currAlarm.setCustomerGroupIds(customerGroupIds);
		} else {
			currAlarm.setCustomerGroupInfo("");
			currAlarm.setCustomerGroupIds(null);
		}

		currAlarm.setCircuitInfo(getCircuitInfo());
		Set<ObjectId> sncCircuitIds = currAlarm.getSncCircuits().stream()
				.map(circuit -> {
					return circuit.getId();
				}).collect(Collectors.toSet());
		currAlarm.setSncCircuitIds(sncCircuitIds);

		Set<ObjectId> channelCircuitIds = currAlarm.getChannelCircuits()
				.stream().map(circuit -> {
					return circuit.getId();
				}).collect(Collectors.toSet());
		currAlarm.setChannelCircuitIds(channelCircuitIds);

		sncCircuits.stream().forEach(circuit -> {
			if (circuit.getUserIds().size() > 0) {
				currAlarm.getUserIds().addAll(circuit.getUserIds());
			}
		});
		channelCircuits.stream().forEach(circuit -> {
			if (circuit.getUserIds().size() > 0) {
				currAlarm.getUserIds().addAll(circuit.getUserIds());
			}
		});
	}

	private AlarmKnowledge getAlarmKnowledge() {
		String key = me.getType() + "~" + alarmEvent.getName();
		;
		if (me.getEms() != null && me.getEms().getVendor() != null) {
			if (me.getType() == MeDeviceType.SDH
					|| me.getType() == MeDeviceType.SWITCH) {
				key = me.getEms().getVendor().getName() + "~"
						+ alarmEvent.getName();
			}
		}
		return alarmKnowledgeCache.getAlarmKnowledge(key);
	}

	private boolean getIsBusiness(AlarmKnowledge alarmKnowledge) {
		if (alarmKnowledge == null) {
			return false;
		} else if (alarmKnowledge.getProblemCause() != null
				&& alarmKnowledge.getProblemCause().equals("客户网管故障")) {
			return true;
		} else {
			return alarmKnowledge.getBusiness();
		}
	}

	private boolean isAlarmOld(Date alarmEventTime, Date currAlarmTime) {
		if (alarmEventTime.after(currAlarmTime)
				|| alarmEventTime.equals(currAlarmTime))
			return true;
		else
			return false;
	}

	private String getDeviceName() {
		String deviceName = "";
		if (alarmEvent.getObjectType() == AlarmObjectType.NE) {
			if (alarmEvent.getName().contains("LPRSA板故障")
					&& alarmEvent.getVendorName().equals(DNConst.COMPANY_HW)) {
				deviceName = alarmEvent.getModelNo() + "-"
						+ alarmEvent.getLinkNo();
			} else
				deviceName = DeviceNameUtil.INSTANCE.getMeInfo(me);
		}
		if (alarmEvent.getObjectType() == AlarmObjectType.TRUNKGROUP) {
			deviceName = alarmEvent.getDeviceName();
		} else if (alarmEvent.getObjectType() == AlarmObjectType.SHELF)
			deviceName = DeviceNameUtil.INSTANCE.getShelfInfo(shelf);
		else if (alarmEvent.getObjectType() == AlarmObjectType.CIRCUITPACK)
			deviceName = DeviceNameUtil.INSTANCE.getCardInfo(me, card);
		else if (alarmEvent.getObjectType() == AlarmObjectType.PTP)
			deviceName = DeviceNameUtil.INSTANCE.getPtpInfo(me, card, port);
		else if (alarmEvent.getObjectType() == AlarmObjectType.CTP)
			deviceName = DeviceNameUtil.INSTANCE
					.getCtpInfo(me, card, port, ctp);
		if (logger.isDebugEnabled())
			logger.debug("deviceName is: "
					+ alarmEvent.getObjectType().getDescription() + ","
					+ deviceName);
		return deviceName;
	}

	private String getCustomerInfo() {
		String info = "";
		StringBuilder nameSb = new StringBuilder();
		if (customers.size() > 0) {
			for (Customer customer : customers) {
				nameSb.append(customer.getName()).append(",");
			}
		}
		info = nameSb.toString();
		int lastIndex = info.lastIndexOf(",");
		info = info.substring(0, lastIndex);
		if (info.length() > 2000) {
			int lastLocation = info.substring(0, 1998).lastIndexOf(",");
			info = info.substring(0, lastLocation) + "等";
		}
		return info;

	}

	private String getCustomerGroupInfo() {
		String info = "";
		StringBuilder nameSb = new StringBuilder();
		if (customerGroups.size() > 0) {
			for (CustomerGroup customerGroup : customerGroups) {
				nameSb.append(customerGroup.getName()).append(",");
			}
		}
		info = nameSb.toString();
		int lastIndex = info.lastIndexOf(",");
		info = info.substring(0, lastIndex);
		if (info.length() > 2000) {
			int lastLocation = info.substring(0, 1998).lastIndexOf(",");
			info = info.substring(0, lastLocation) + "等";
		}
		return info;
	}

	private String getCircuitInfo() {
		Set<Circuit> circuits = new HashSet<Circuit>();
		circuits.addAll(sncCircuits);
		circuits.addAll(channelCircuits);
		String info = "";
		StringBuilder nameSb = new StringBuilder();
		for (Circuit circuit : circuits) {
			nameSb.append(circuit.getNo()).append(",");
		}
		info = nameSb.toString();
		int lastIndex = info.lastIndexOf(",");
		info = info.substring(0, lastIndex);
		if (info.length() > 2000) {
			int lastLocation = info.substring(0, 1998).lastIndexOf(",");
			info = info.substring(0, lastLocation) + "等";
		}
		return info;
	}
}

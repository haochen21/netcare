package nsgsw1.netcare.service.internal;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import nsgsw1.netcare.model.alarm.AlarmInfo;
import nsgsw1.netcare.model.alarm.AlarmKnowledge;
import nsgsw1.netcare.model.alarm.ClearInfo;
import nsgsw1.netcare.model.alarm.CurrAlarm;
import nsgsw1.netcare.model.alarm.Fault;
import nsgsw1.netcare.model.alarm.HisAlarm;
import nsgsw1.netcare.model.alarm.HisFault;
import nsgsw1.netcare.model.alarm.constant.FaultOperation;
import nsgsw1.netcare.model.circuit.Circuit;
import nsgsw1.netcare.model.circuit.constant.CircuitBizStatus;
import nsgsw1.netcare.model.customer.Customer;
import nsgsw1.netcare.model.customer.CustomerGroup;
import nsgsw1.netcare.repository.alarm.AlarmInfoRepository;
import nsgsw1.netcare.repository.alarm.AlarmKnowledgeRepository;
import nsgsw1.netcare.repository.alarm.CurrAlarmRepository;
import nsgsw1.netcare.repository.alarm.FaultRepository;
import nsgsw1.netcare.repository.alarm.HisAlarmRepository;
import nsgsw1.netcare.repository.alarm.HisFaultRepository;
import nsgsw1.netcare.repository.circuit.CircuitRepository;
import nsgsw1.netcare.service.AlarmService;
import nsgsw1.netcare.service.CustomerService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	AlarmKnowledgeRepository alarmKnowledgeRepository;

	@Autowired
	CurrAlarmRepository currAlarmRepository;

	@Autowired
	HisAlarmRepository hisAlarmRepository;

	@Autowired
	AlarmInfoRepository alarmInfoRepository;

	@Autowired
	FaultRepository faultRepository;

	@Autowired
	HisFaultRepository hisFaultRepository;

	@Autowired
	CustomerService customerService;

	@Autowired
	CircuitRepository circuitRepository;

	@Override
	public Collection<AlarmKnowledge> findAllAlarmKnowledges() {
		return alarmKnowledgeRepository.findAll();
	}

	@Override
	public AlarmKnowledge saveAlarmKnowledge(AlarmKnowledge alarmKnowledge) {
		return alarmKnowledgeRepository.save(alarmKnowledge);
	}

	@Override
	public Collection<CurrAlarm> findAlarmsByEmsAndUpdateTime(String emsName,
			Date updatetime) {
		return currAlarmRepository.findAlarmsByEmsAndUpdateTime(emsName,
				updatetime);
	}

	@Override
	public Collection<CurrAlarm> findAlarmsByUpdateTime(Date updateTime) {
		return currAlarmRepository.findAlarmsByUpdateTime(updateTime);
	}

	@Override
	public CurrAlarm findCurrAlarmByUid(String uid) {
		return currAlarmRepository.findByUid(uid);
	}

	@Override
	public CurrAlarm saveCurrAlarm(CurrAlarm currAlarm) {
		if (currAlarm.getId() == null) {
			createAlarmInfo(currAlarm);
		}
		currAlarmRepository.save(currAlarm);
		return currAlarm;
	}

	@Override
	public CurrAlarm clearCurrAlarm(ClearInfo clearInfo, CurrAlarm currAlarm) {
		HisAlarm hisAlarm = createHisAlarm(clearInfo, currAlarm);
		hisAlarmRepository.save(hisAlarm);
		updateAlarmInfo(hisAlarm);
		currAlarmRepository.delete(currAlarm);
		currAlarm.setHisAlarmId(hisAlarm.getId());
		// 如果电路没有业务告警且电路的业务态度为备份，改变电路的业务状态为正常
		currAlarm
				.getSncCircuits()
				.stream()
				.forEach(
						circuit -> {
							if (circuit.getBizStatus() != null
									&& circuit.getBizStatus() == CircuitBizStatus.BACKUP) {
								long count = currAlarmRepository
										.bizAlarmCountByCircuit(circuit.getId());
								if (count == 0) {
									circuitRepository.updateCircuitBizStatus(
											circuit.getId(),
											CircuitBizStatus.NORMAL);
								}
							}
						});
		return currAlarm;
	}

	private HisAlarm createHisAlarm(ClearInfo clearInfo, CurrAlarm currAlarm) {
		HisAlarm hisAlarm = new HisAlarm();
		hisAlarm.setClearInfo(clearInfo);
		hisAlarm.setUid(currAlarm.getUid());
		hisAlarm.setDeviceUid(currAlarm.getDeviceUid());
		hisAlarm.setName(currAlarm.getName());
		hisAlarm.setSeverity(currAlarm.getSeverity());
		hisAlarm.setAck(currAlarm.getAck());
		hisAlarm.setAckInfo(currAlarm.getAckInfo());
		hisAlarm.setCategory(currAlarm.getCategory());
		hisAlarm.setType(currAlarm.getType());
		hisAlarm.setObjectType(currAlarm.getObjectType());
		hisAlarm.setBusiness(currAlarm.getBusiness());
		hisAlarm.setCreateTime(currAlarm.getCreateTime());
		hisAlarm.setUpdateTime(currAlarm.getUpdateTime());
		hisAlarm.setClearTime(currAlarm.getClearTime());
		hisAlarm.setEmsCreateTime(currAlarm.getEmsCreateTime());
		hisAlarm.setEmsUpdateTime(currAlarm.getEmsUpdateTime());
		hisAlarm.setEmsClearTime(currAlarm.getEmsClearTime());
		hisAlarm.setMeCreateTime(currAlarm.getMeCreateTime());
		hisAlarm.setMeUpdateTime(currAlarm.getMeUpdateTime());
		hisAlarm.setMeClearTime(currAlarm.getMeClearTime());
		hisAlarm.setDescription(currAlarm.getDescription());
		hisAlarm.setAdditionalInfo(currAlarm.getAdditionalInfo());
		hisAlarm.setDeviceName(currAlarm.getDeviceName());
		hisAlarm.setMe(currAlarm.getMe());
		hisAlarm.setShelf(currAlarm.getShelf());
		hisAlarm.setSlot(currAlarm.getSlot());
		hisAlarm.setCard(currAlarm.getCard());
		hisAlarm.setPort(currAlarm.getPort());
		hisAlarm.setCtp(currAlarm.getCtp());

		hisAlarm.setUserIds(currAlarm.getUserIds());

		hisAlarm.setCustomerInfo(currAlarm.getCustomerInfo());
		hisAlarm.setCustomerIds(currAlarm.getCustomerIds());

		hisAlarm.setCustomerGroupInfo(currAlarm.getCustomerGroupInfo());
		hisAlarm.setCustomerGroupIds(currAlarm.getCustomerGroupIds());

		hisAlarm.setCircuitInfo(currAlarm.getCircuitInfo());
		hisAlarm.setSncCircuitIds(currAlarm.getSncCircuitIds());
		hisAlarm.setChannelCircuitIds(currAlarm.getChannelCircuitIds());

		return hisAlarm;
	}

	private void createAlarmInfo(CurrAlarm currAlarm) {
		AlarmInfo alarmInfo = new AlarmInfo();
		alarmInfo.setDeviceAlarmUid(currAlarm.getDeviceUid() + "~"
				+ currAlarm.getName());
		alarmInfo.setUid(currAlarm.getUid());
		alarmInfo.setSeverity(currAlarm.getSeverity());
		alarmInfo.setCreateTime(currAlarm.getUpdateTime());
		alarmInfo.setEmsCreateTime(currAlarm.getEmsCreateTime());
		alarmInfo.setMeCreateTime(currAlarm.getMeCreateTime());
		alarmInfo.setSeverity(currAlarm.getSeverity());
		alarmInfoRepository.save(alarmInfo);
	}

	private void updateAlarmInfo(HisAlarm hisAlarm) {
		AlarmInfo alarmInfo = alarmInfoRepository.findByUid(hisAlarm.getUid());
		if (alarmInfo != null) {
			alarmInfo.setClearTime(hisAlarm.getClearTime());
			alarmInfo.setEmsClearTime(hisAlarm.getEmsClearTime());
			alarmInfo.setMeClearTime(hisAlarm.getMeClearTime());
			alarmInfoRepository.save(alarmInfo);
		}
	}

	@Override
	public Fault createFault(Circuit circuit, CurrAlarm currAlarm) {
		Fault fault = faultRepository.findByCircuitId(circuit.getId());
		if (fault != null && circuit.getBizStatus() != null
				&& circuit.getBizStatus() == CircuitBizStatus.BACKUP) {
			fault.setOperation(FaultOperation.DELETE);
			faultRepository.delete(fault);
			return fault;
		} else {
			if (fault == null) {
				fault = new Fault();
				fault.setOperation(FaultOperation.NEW);
				fault.setBeginTime(new Date());
				fault.setMeCreateTime(currAlarm.getMeCreateTime());
				fault.setFaultAck(false);
			} else {
				fault.setOperation(FaultOperation.UPDATE);
				Date meCreateTime = currAlarm.getMeCreateTime();
				if (fault.getCurrAlarmIds() != null
						&& fault.getCurrAlarmIds().size() > 0) {
					Collection<CurrAlarm> currAlarms = currAlarmRepository
							.findByIds(fault.getCurrAlarmIds());
					fault.getCurrAlarms().addAll(currAlarms);
					if (currAlarms != null && currAlarms.size() > 0) {
						CurrAlarm minMeCreateTimeAlarm = currAlarms
								.stream()
								.min((alarm1, alarm2) -> alarm1
										.getMeCreateTime().compareTo(
												alarm2.getMeCreateTime()))
								.get();
						if (minMeCreateTimeAlarm.getMeCreateTime().before(
								meCreateTime)) {
							meCreateTime = minMeCreateTimeAlarm
									.getMeCreateTime();
						}
					}
				}
				fault.setMeCreateTime(meCreateTime);
			}

			fault.setCircuitId(circuit.getId());
			fault.setNo(circuit.getNo());
			fault.setGroupNo(circuit.getGroupNo());
			fault.setRate(circuit.getRate());
			fault.setBizState(circuit.getBizState());
			fault.setBizProperty(circuit.getBizProperty());

			fault.getCurrAlarms().add(currAlarm);
			Set<ObjectId> currAlarmIds = fault.getCurrAlarms().stream()
					.map(alarm -> {
						return alarm.getId();
					}).collect(Collectors.toSet());
			fault.setCurrAlarmIds(currAlarmIds);

			Set<ObjectId> hisAlarmIds = fault.getHisAlarms().stream()
					.map(alarm -> {
						return alarm.getId();
					}).collect(Collectors.toSet());
			fault.setHisAlarmIds(hisAlarmIds);

			fault.setCircuit(circuit);
			if (fault.getCircuit().getUserIds().size() > 0) {
				fault.setUserIds(fault.getCircuit().getUserIds());
			}

			makeUpFault(fault, currAlarm);
			fault = faultRepository.save(fault);
			if (fault.getOperation() == FaultOperation.NEW)
				circuitRepository.updateCircuitFault(circuit.getId(), true);
			else if (fault.getOperation() == FaultOperation.DELETE)
				circuitRepository.updateCircuitFault(circuit.getId(), false);
			return fault;
		}
	}

	@Override
	public Fault clearFault(Circuit circuit, CurrAlarm currAlarm) {
		Fault fault = faultRepository.findByCircuitId(circuit.getId());
		if (fault != null) {
			fault.setCircuit(circuit);
			if (fault.getCircuit().getUserIds().size() > 0) {
				fault.setUserIds(fault.getCircuit().getUserIds());
			}
			fault.getCurrAlarmIds().remove(currAlarm.getId());
			fault.getHisAlarmIds().add(currAlarm.getHisAlarmId());
			if (fault.getCurrAlarmIds().size() > 0) {
				fault.setOperation(FaultOperation.UPDATE);
				faultRepository.save(fault);
			} else {
				createHisFault(currAlarm, fault);
				fault.setOperation(FaultOperation.DELETE);
				faultRepository.delete(fault);
			}

			if (fault.getCurrAlarmIds() != null
					&& fault.getCurrAlarmIds().size() > 0) {
				Collection<CurrAlarm> currAlarms = currAlarmRepository
						.findByIds(fault.getCurrAlarmIds());
				fault.getCurrAlarms().addAll(currAlarms);
			}

			if (fault.getOperation() == FaultOperation.DELETE)
				circuitRepository.updateCircuitFault(circuit.getId(), false);
			makeUpFault(fault, currAlarm);
		}
		return fault;
	}

	private HisFault createHisFault(CurrAlarm currAlarm, Fault fault) {
		HisFault hisFault = new HisFault();
		hisFault.setCircuitId(fault.getCircuitId());
		hisFault.setNo(fault.getNo());
		hisFault.setGroupNo(fault.getGroupNo());
		hisFault.setRate(fault.getRate());
		hisFault.setBizState(fault.getBizState());
		hisFault.setBizProperty(fault.getBizProperty());
		hisFault.setFaultAck(fault.getFaultAck());
		hisFault.setBeginTime(fault.getBeginTime());
		hisFault.setEndTime(new Date());
		hisFault.setMeCreateTime(fault.getMeCreateTime());
		hisFault.setMeClearTime(currAlarm.getMeClearTime());
		hisFault.setHisAlarmIds(fault.getHisAlarmIds());
		hisFault.setUserIds(fault.getUserIds());
		hisFault.setCustomerIds(fault.getCustomerIds());
		hisFault.setCustomerGroupIds(fault.getCustomerGroupIds());
		hisFault.setDuration((hisFault.getMeClearTime().getTime() - hisFault
				.getMeClearTime().getTime()) / 1000);
		return hisFaultRepository.save(hisFault);
	}

	private void makeUpFault(Fault fault, CurrAlarm currAlarm) {
		Collection<ObjectId> customerIds = new HashSet<>();
		Collection<ObjectId> customerGroupIds = new HashSet<>();

		if (fault.getCircuit().getaCustomerId() != null) {
			Customer customer = customerService.findCustomerById(fault
					.getCircuit().getaCustomerId());
			fault.getCircuit().setaCustomer(customer);
			customerIds.add(fault.getCircuit().getaCustomerId());
			customerGroupIds.addAll(customer.getCustomerGroupIds());
		}

		if (fault.getCircuit().getzCustomerId() != null) {
			Customer customer = customerService.findCustomerById(fault
					.getCircuit().getzCustomerId());
			fault.getCircuit().setzCustomer(customer);
			customerIds.add(fault.getCircuit().getzCustomerId());
			customerGroupIds.addAll(customer.getCustomerGroupIds());
		}

		if (customerIds.size() > 0) {
			List<Customer> queryCustomers = customerService
					.findCustomerByIds(customerIds);
			fault.setCustomers(queryCustomers);
			fault.setCustomerIds(customerIds);

			StringBuilder nameSb = new StringBuilder();
			for (Customer customer : queryCustomers) {
				nameSb.append(customer.getName()).append(",");
			}
			String info = nameSb.toString();
			int lastIndex = info.lastIndexOf(",");
			fault.setCustomerInfo(info.substring(0, lastIndex));
		}

		if (customerGroupIds.size() > 0) {
			List<CustomerGroup> queryCustomerGroups = customerService
					.findCustomerGroupByIds(customerGroupIds);
			fault.setCustomerGroups(queryCustomerGroups);
			fault.setCustomerGroupIds(customerGroupIds);
			StringBuilder nameSb = new StringBuilder();
			for (CustomerGroup customerGroup : queryCustomerGroups) {
				nameSb.append(customerGroup.getName()).append(",");
			}
			String info = nameSb.toString();
			int lastIndex = info.lastIndexOf(",");
			fault.setCustomerGroupInfo(info.substring(0, lastIndex));
		}
	}
}

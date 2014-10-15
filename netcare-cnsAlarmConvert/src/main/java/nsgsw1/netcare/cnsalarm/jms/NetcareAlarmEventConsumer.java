package nsgsw1.netcare.cnsalarm.jms;

import java.util.concurrent.BlockingQueue;

import nsgsw1.cns.domain.alarm.AlarmEvent;
import nsgsw1.netcare.model.alarm.constant.AlarmCategory;
import nsgsw1.netcare.model.alarm.constant.AlarmObjectType;
import nsgsw1.netcare.model.alarm.constant.AlarmSeverity;
import nsgsw1.netcare.model.alarm.constant.AlarmEventStatus;
import nsgsw1.netcare.model.alarm.constant.AlarmType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetcareAlarmEventConsumer extends Thread {

	private SendNetcareAlarmEvent sendNetcareAlarmEvent;

	private BlockingQueue<AlarmEvent> alarmEventQueue;

	private int sleepTime;

	private final static Logger logger = LoggerFactory
			.getLogger(NetcareAlarmEventConsumer.class);

	public NetcareAlarmEventConsumer(
			SendNetcareAlarmEvent sendNetcareAlarmEvent,
			BlockingQueue<AlarmEvent> alarmEventQueue, int sleepTime) {
		super();
		this.sendNetcareAlarmEvent = sendNetcareAlarmEvent;
		this.alarmEventQueue = alarmEventQueue;
		this.sleepTime = sleepTime;
	}

	@Override
	public void run() {
		while (!super.isInterrupted()) {
			AlarmEvent alarmEvent = null;
			try {
				if (alarmEventQueue.size() > 0) {
					alarmEvent = alarmEventQueue.take();
					nsgsw1.netcare.model.alarm.AlarmEvent netCareAlarmEvent = new nsgsw1.netcare.model.alarm.AlarmEvent();
					netCareAlarmEvent.setVendorName(alarmEvent.getVendorName());
					netCareAlarmEvent.setEmsName(alarmEvent.getEmsName());
					netCareAlarmEvent.setDeviceName(alarmEvent.getDeviceName());
					netCareAlarmEvent.setEmsDeviceName(alarmEvent
							.getEmsDeviceName());
					if (alarmEvent.getCategory() != null)
						netCareAlarmEvent.setCategory(AlarmCategory
								.getFieldTypeByOrdinal(alarmEvent.getCategory()
										.ordinal()));
					if (alarmEvent.getObjectType() != null)
						netCareAlarmEvent.setObjectType(AlarmObjectType
								.getFieldTypeByOrdinal(alarmEvent
										.getObjectType().ordinal()));
					netCareAlarmEvent.setName(alarmEvent.getName());
					netCareAlarmEvent.setModelNo(alarmEvent.getModelNo());
					netCareAlarmEvent.setRackNo(alarmEvent.getRackNo());
					netCareAlarmEvent.setShelfNo(alarmEvent.getShelfNo());
					netCareAlarmEvent.setSlotNo(alarmEvent.getSlotNo());
					netCareAlarmEvent.setCardNo(alarmEvent.getCardNo());
					netCareAlarmEvent.setPortNo(alarmEvent.getPortNo());
					netCareAlarmEvent.setCtpNo(alarmEvent.getCtpNo());
					netCareAlarmEvent.setLinkNo(alarmEvent.getLinkNo());
					netCareAlarmEvent.setSerialNo(alarmEvent.getSerialNo());
					netCareAlarmEvent.setEmsCreateTime(alarmEvent
							.getEmsCreateTime());
					netCareAlarmEvent.setMeCreateTime(alarmEvent
							.getMeCreateTime());
					if (alarmEvent.getSeverity() != null)
						netCareAlarmEvent.setSeverity(AlarmSeverity
								.getSeverityByOrdinal(alarmEvent.getSeverity()
										.ordinal()));
					if (alarmEvent.getStatus() != null)
						netCareAlarmEvent.setStatus(AlarmEventStatus
								.getStatusByOrdinal(alarmEvent.getStatus()
										.ordinal()));
					netCareAlarmEvent.setCause(alarmEvent.getCause());
					if (alarmEvent.getType() != null)
						netCareAlarmEvent.setType(AlarmType
								.getTypeByOrdinal(alarmEvent.getType()
										.ordinal()));
					netCareAlarmEvent.setCode(alarmEvent.getCode());
					netCareAlarmEvent.setSpcIndex(alarmEvent.getSpcIndex());
					netCareAlarmEvent.setsNode(alarmEvent.getsNode());
					netCareAlarmEvent.setSourceAddress(alarmEvent
							.getSourceAddress());
					netCareAlarmEvent.setTargetAddress(alarmEvent
							.getTargetAddress());
					netCareAlarmEvent.setSource(alarmEvent.getSource());
					netCareAlarmEvent.setAdditionalInfo(alarmEvent
							.getAdditionalInfo());
					netCareAlarmEvent.setRepairRecommend(alarmEvent
							.getRepairRecommend());
					netCareAlarmEvent.setOriginalText(alarmEvent
							.getOriginalText());
					netCareAlarmEvent.setDeviceUid(alarmEvent.getDeviceUid());
					netCareAlarmEvent.setDeviceLocationInfo(alarmEvent
							.getDeviceLocationInfo());
					netCareAlarmEvent.setUid(alarmEvent.getUid());
					netCareAlarmEvent.setSyncAlarm(alarmEvent.isSyncAlarm());
					sendNetcareAlarmEvent.sendingAlarmEvent(netCareAlarmEvent);
					logger.info("send alarmEvent success,alarm name is: "
							+ alarmEvent.getName() + ",uid is:"
							+ alarmEvent.getUid());
				} else {
					Thread.sleep(sleepTime);
				}
			} catch (Exception ex) {
				logger.info("send alarm error.", ex);
			} finally {
				alarmEvent = null;
			}
		}
	}

	@Override
	public synchronized void start() {
		logger.info("Start send alarmEvent thread,name is:" + this.getName());
		super.start();
	}

	@Override
	public void interrupt() {
		logger.info("Interrupt send alarmEvent thread,name is:"
				+ this.getName());
		super.interrupt();
	}

}


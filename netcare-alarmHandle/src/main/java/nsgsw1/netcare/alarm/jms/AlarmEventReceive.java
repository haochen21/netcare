package nsgsw1.netcare.alarm.jms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import nsgsw1.netcare.alarm.cache.HandlingEventCache;
import nsgsw1.netcare.alarm.consumer.AlarmEventConsumer;
import nsgsw1.netcare.alarm.consumer.FaultConsumer;
import nsgsw1.netcare.alarm.consumer.SyncAlarmConsumer;
import nsgsw1.netcare.alarm.util.FaultCircuit;
import nsgsw1.netcare.model.alarm.AlarmEvent;
import nsgsw1.netcare.model.alarm.CurrAlarm;
import nsgsw1.netcare.model.alarm.constant.AlarmCategory;
import nsgsw1.netcare.model.alarm.constant.AlarmEventStatus;
import nsgsw1.netcare.service.AlarmService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlarmEventReceive implements MessageListener {

	// 告警线程数
	private int alarmEventThreadNum;

	// 同步告警线程数
	private int syncAlarmThreadNum;

	// 故障线程数
	private int faultThreadNum;

	// 告警队列最大数
	private int maxAlarmQueueNum;

	// 线程休眠时间
	private int sleepTime;

	// 告警处理最长时间，秒
	private int handleTime;

	// 清除告警延迟时间，秒
	private int clearDelayTime;

	// 故障告警延迟时间，秒
	private int faultDelayTime;

	private HandlingEventCache handlingEventCache;

	private Map<String, DelayQueue<AlarmEvent>> alarmQueueMap;

	private Map<String, DelayQueue<AlarmEvent>> syncAlarmQueueMap;

	private BlockingDeque<FaultCircuit> faultQueue;

	private List<Thread> alarmEventConsumers;

	private List<Thread> syncAlarmConsumers;

	private List<Thread> faultConsumers;

	private AlarmService alarmService;

	private final static Logger logger = LoggerFactory
			.getLogger(AlarmEventReceive.class);

	public AlarmEventReceive() {

	}

	public int getAlarmEventThreadNum() {
		return alarmEventThreadNum;
	}

	public void setAlarmEventThreadNum(int alarmEventThreadNum) {
		this.alarmEventThreadNum = alarmEventThreadNum;
	}

	public int getSyncAlarmThreadNum() {
		return syncAlarmThreadNum;
	}

	public void setSyncAlarmThreadNum(int syncAlarmThreadNum) {
		this.syncAlarmThreadNum = syncAlarmThreadNum;
	}

	public int getFaultThreadNum() {
		return faultThreadNum;
	}

	public void setFaultThreadNum(int faultThreadNum) {
		this.faultThreadNum = faultThreadNum;
	}

	public int getMaxAlarmQueueNum() {
		return maxAlarmQueueNum;
	}

	public void setMaxAlarmQueueNum(int maxAlarmQueueNum) {
		this.maxAlarmQueueNum = maxAlarmQueueNum;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	public int getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(int handleTime) {
		this.handleTime = handleTime;
	}

	public int getClearDelayTime() {
		return clearDelayTime;
	}

	public void setClearDelayTime(int clearDelayTime) {
		this.clearDelayTime = clearDelayTime;
	}

	public int getFaultDelayTime() {
		return faultDelayTime;
	}

	public void setFaultDelayTime(int faultDelayTime) {
		this.faultDelayTime = faultDelayTime;
	}

	public HandlingEventCache getHandlingEventCache() {
		return handlingEventCache;
	}

	public void setHandlingEventCache(HandlingEventCache handlingEventCache) {
		this.handlingEventCache = handlingEventCache;
	}

	public AlarmService getAlarmService() {
		return alarmService;
	}

	public void setAlarmService(AlarmService alarmService) {
		this.alarmService = alarmService;
	}

	public void start() {
		alarmQueueMap = new ConcurrentHashMap<String, DelayQueue<AlarmEvent>>();
		syncAlarmQueueMap = new ConcurrentHashMap<String, DelayQueue<AlarmEvent>>();
		faultQueue = new LinkedBlockingDeque<>();
		alarmEventConsumers = new ArrayList<>();
		syncAlarmConsumers = new ArrayList<>();
		faultConsumers = new ArrayList<>();

		try {
			Thread.sleep(1 * 1000);
		} catch (Exception ex) {
			logger.info("alarm dispatch error", ex);
		}
		// 启动故障处理类
		for (int i = 0; i < faultThreadNum; i++) {
			FaultConsumer consumer = new FaultConsumer();
			consumer.setFaultQueue(faultQueue);
			consumer.setSleepTime(sleepTime);
			consumer.setHandleTime(handleTime);
			consumer.setHandlingEventCache(handlingEventCache);
			consumer.setName("fault-consumer-thread-" + i);
			faultConsumers.add(consumer);
			consumer.start();
		}
	}

	public void stop() {
		for (Thread consumer : alarmEventConsumers) {
			consumer.interrupt();
		}
		alarmEventConsumers.clear();

		for (Map.Entry<String, DelayQueue<AlarmEvent>> entry : alarmQueueMap
				.entrySet()) {
			entry.getValue().clear();
		}
		alarmQueueMap.clear();
		alarmQueueMap = null;

		syncAlarmQueueMap.clear();
		syncAlarmQueueMap = null;
		for (Thread consumer : syncAlarmConsumers) {
			consumer.interrupt();
		}

		faultQueue.clear();
		for (Thread consumer : faultConsumers) {
			consumer.interrupt();
		}
		faultConsumers.clear();
	}

	@Override
	public void onMessage(Message message) {
		AlarmEvent alarmEvent = null;
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage objectMessage = (ObjectMessage) message;
				if (objectMessage.getObject() instanceof AlarmEvent) {
					alarmEvent = (AlarmEvent) objectMessage.getObject();
					if (alarmEvent.getStatus() == AlarmEventStatus.ACTIVE) {
						if (alarmEvent.isSyncAlarm())
							addToSyncAlarmQueue(alarmEvent);
						else
							addToAlarmEventQueue(alarmEvent);
					} else {
						addToAlarmEventQueue(alarmEvent);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			alarmEvent = null;
			message = null;
		}
	}

	private void createAlarmEventConsumer(int threadNum, String emsName) {
		for (int i = 0; i < threadNum; i++) {
			AlarmEventConsumer consumer = new AlarmEventConsumer();
			consumer.setAlarmQueue(alarmQueueMap.get(emsName));
			consumer.setSleepTime(sleepTime);
			consumer.setHandleTime(handleTime);
			consumer.setHandlingEventCache(handlingEventCache);
			consumer.setAlarmEventReceive(this);
			consumer.setName(emsName + "~" + i);
			alarmEventConsumers.add(consumer);
			consumer.start();
		}
	}

	public void addToAlarmEventQueue(AlarmEvent alarmEvent) {
		String emsName = null;
		if (alarmEvent.getCategory() == AlarmCategory.SDH) {
			emsName = alarmEvent.getEmsName();
			if (emsName == null) {
				logger.info("emsName is null,alarmEvent is:"
						+ alarmEvent.toString());
				emsName = "SDH";
			}
		} else if (alarmEvent.getCategory() != null) {
			emsName = alarmEvent.getCategory().name();
		}
		if (alarmQueueMap.get(emsName) == null) {
			DelayQueue<AlarmEvent> alarmQueue = new DelayQueue<AlarmEvent>();
			alarmQueueMap.put(emsName, alarmQueue);
			createAlarmEventConsumer(alarmEventThreadNum, emsName);
		}
		DelayQueue<AlarmEvent> queue = alarmQueueMap.get(emsName);
		if (alarmEvent.getStatus() == AlarmEventStatus.ACTIVE) {
			if (queue.contains(alarmEvent)) {
				logger.info("active alarmEvent is in queue,discard!"
						+ alarmEvent.toString());
				return;
			} else if (handlingEventCache.isExistInAlarmEvent(alarmEvent
					.getUid())) {
				logger.info("active alarmEvent is handling,discard!"
						+ alarmEvent.toString());
				return;
			}
		}
		if (queue.size() > maxAlarmQueueNum) {
			logger.info("alarmQueue size is beyond,uid is:"
					+ alarmEvent.toString());
		} else {
			if (alarmEvent.getStatus() == AlarmEventStatus.ACTIVE) {
				alarmEvent.setDelayTime(0);
				queue.put(alarmEvent);
			} else if (alarmEvent.getStatus() == AlarmEventStatus.CLEARED) {
				int multiple = 1;
				if (queue.contains(alarmEvent)) {
					if (logger.isDebugEnabled())
						logger.debug("alarmEvent is in queue,multiple is 2,"
								+ alarmEvent.toString());
					multiple = 2;
				}
				if (alarmEvent.getCategory() == AlarmCategory.SDH)
					alarmEvent.setDelayTime(multiple * clearDelayTime * 1000);
				else
					alarmEvent.setDelayTime(multiple * 30 * 1000);
				queue.put(alarmEvent);
			}
		}
	}

	public void addToSyncAlarmQueue(AlarmEvent alarmEvent) {
		String emsName = null;
		if (alarmEvent.getCategory() == AlarmCategory.SDH) {
			emsName = alarmEvent.getEmsName();
			if (emsName == null) {
				logger.info("emsName is null,alarmEvent is:"
						+ alarmEvent.toString());
				emsName = "SDH";
			}
		} else if (alarmEvent.getCategory() != null) {
			emsName = alarmEvent.getCategory().name();
		}
		if (emsName == null) {
			logger.info("emsName is null,alarmEvent is:"
					+ alarmEvent.toString());
		}
		if (syncAlarmQueueMap.get(emsName) == null) {
			DelayQueue<AlarmEvent> alarmQueue = new DelayQueue<AlarmEvent>();
			syncAlarmQueueMap.put(emsName, alarmQueue);
			createSyncAlarmEventConsumer(syncAlarmThreadNum, emsName);
		}
		if (alarmEvent.getStatus() == AlarmEventStatus.CLEARED) {
			// 同步结束后，清除没有同步到的告警
			alarmEvent.setDelayTime(1000);
			syncAlarmQueueMap.get(emsName).add(alarmEvent);
		} else if (alarmEvent.getStatus() == AlarmEventStatus.ACTIVE) {
			if (alarmEvent.getName().equals("alarmSyncStart")) {
				for (Thread thread : syncAlarmConsumers) {
					if (thread.getName().contains("syncAlarm~" + emsName)) {
						SyncAlarmConsumer consumer = (SyncAlarmConsumer) thread;
						consumer.setFirstSyncDate(new Date());
					}
				}
			} else {
				alarmEvent.setDelayTime(0);
				syncAlarmQueueMap.get(emsName).add(alarmEvent);
			}
		}
	}

	private void createSyncAlarmEventConsumer(int threadNum, String emsName) {
		for (int i = 0; i < threadNum; i++) {
			SyncAlarmConsumer consumer = new SyncAlarmConsumer();
			consumer.setAlarmQueue(syncAlarmQueueMap.get(emsName));
			consumer.setSleepTime(sleepTime);
			consumer.setHandleTime(handleTime);
			consumer.setHandlingEventCache(handlingEventCache);
			consumer.setAlarmEventReceive(this);
			consumer.setAlarmService(alarmService);
			consumer.setName("syncAlarm~" + emsName + "~" + i);
			syncAlarmConsumers.add(consumer);
			consumer.start();
		}
	}

	public void dispatchToFaultQueue(CurrAlarm currAlarm) {
		if (currAlarm.getSncCircuits().size() > 0) {
			currAlarm.getSncCircuits().forEach(circuit -> {
				FaultCircuit fc = new FaultCircuit();
				fc.setCircuitNo(circuit.getNo());
				fc.setCurrAlarm(currAlarm);
				faultQueue.offerLast(fc);
			});
		}
	}
}

package nsgsw1.netcare.alarm.consumer;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import nsgsw1.netcare.alarm.cache.HandlingEventCache;
import nsgsw1.netcare.alarm.handle.AccessEventHandle;
import nsgsw1.netcare.alarm.handle.AlarmHandleImpl;
import nsgsw1.netcare.alarm.handle.DataEventHandle;
import nsgsw1.netcare.alarm.handle.SdhEventHandle;
import nsgsw1.netcare.alarm.handle.SwitchEventHandle;
import nsgsw1.netcare.alarm.jms.AlarmEventReceive;
import nsgsw1.netcare.alarm.util.GlobalContext;
import nsgsw1.netcare.model.alarm.AlarmEvent;
import nsgsw1.netcare.model.alarm.CurrAlarm;
import nsgsw1.netcare.model.alarm.constant.AlarmCategory;
import nsgsw1.netcare.model.alarm.constant.AlarmEventStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class AlarmEventConsumer extends Thread {

	private DelayQueue<AlarmEvent> alarmQueue;

	private int sleepTime;

	private int handleTime;

	private AlarmEventReceive alarmEventReceive;

	private HandlingEventCache handlingEventCache;

	private final static Logger logger = LoggerFactory
			.getLogger(AlarmEventConsumer.class);

	public AlarmEventConsumer() {

	}

	public DelayQueue<AlarmEvent> getAlarmQueue() {
		return alarmQueue;
	}

	public void setAlarmQueue(DelayQueue<AlarmEvent> alarmQueue) {
		this.alarmQueue = alarmQueue;
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

	public AlarmEventReceive getAlarmEventReceive() {
		return alarmEventReceive;
	}

	public void setAlarmEventReceive(AlarmEventReceive alarmEventReceive) {
		this.alarmEventReceive = alarmEventReceive;
	}

	public HandlingEventCache getHandlingEventCache() {
		return handlingEventCache;
	}

	public void setHandlingEventCache(HandlingEventCache handlingEventCache) {
		this.handlingEventCache = handlingEventCache;
	}

	@Override
	public void run() {
		while (!super.isInterrupted()) {
			AlarmEvent alarmEvent = null;
			AlarmHandleImpl handle = null;
			FutureTask<CurrAlarm> task = null;
			boolean isHandle = true;
			if (alarmQueue.size() > 0) {
				try {
					alarmEvent = alarmQueue.take();
				} catch (InterruptedException ex) {
					logger.info(this.getName()
							+ " handle alarmEvent InterruptedException.", ex);
					continue;
				}

				if (handlingEventCache.isExistInAlarmEvent(alarmEvent.getUid())) {
					if (alarmEvent.getStatus() == AlarmEventStatus.ACTIVE) {
						logger.info(this.getName()
								+ ",active alarmEvent is handling,discard it,event is: "
								+ alarmEvent.toString());
					} else {
						logger.info(this.getName()
								+ ",alarmEvent is handling,clear event reinput queue,event is: "
								+ alarmEvent.toString());
						alarmEventReceive.addToAlarmEventQueue(alarmEvent);
					}
					continue;
				}

				handlingEventCache.addAlarmEvent(alarmEvent.getUid());

				while (isHandle) {
					if (alarmEvent.getCategory() == AlarmCategory.SDH) {
						handle = (SdhEventHandle) GlobalContext.getInstance()
								.getApplicationContext()
								.getBean("sdhEventHandle");
					} else if (alarmEvent.getCategory() == AlarmCategory.DATA) {
						handle = (DataEventHandle) GlobalContext.getInstance()
								.getApplicationContext()
								.getBean("dataEventHandle");
					} else if (alarmEvent.getCategory() == AlarmCategory.SWITCH) {
						handle = (SwitchEventHandle) GlobalContext
								.getInstance().getApplicationContext()
								.getBean("switchEventHandle");
					} else if (alarmEvent.getCategory() == AlarmCategory.ACCESS) {
						handle = (AccessEventHandle) GlobalContext
								.getInstance().getApplicationContext()
								.getBean("accessEventHandle");
					}

					if (handle == null) {
						logger.info(this.getName() + " handle is null,uid is:"
								+ alarmEvent.getUid());
						isHandle = false;
					} else {
						try {
							StopWatch stopWatch = new StopWatch();
							stopWatch.start();

							handle.setAlarmEvent(alarmEvent);
							task = new FutureTask<>(handle);
							Thread thread = new Thread(task);
							thread.start();
							CurrAlarm currAlarm = task.get(handleTime,
									TimeUnit.SECONDS);
							if (currAlarm != null) {
								alarmEventReceive
										.dispatchToFaultQueue(currAlarm);
							}
							stopWatch.stop();
							long time = stopWatch.getTotalTimeMillis();

							StringBuilder sb = new StringBuilder();
							sb.append(this.getName() + ",");
							sb.append(handle.getClass().getName());
							sb.append(" handle an alarmEvent,time is: ")
									.append(time);
							sb.append("ms,type is: ");
							sb.append(alarmEvent.getObjectType() == null ? ""
									: alarmEvent.getObjectType()
											.getDescription());
							sb.append(",size is:").append(alarmQueue.size());
							logger.info(sb.toString());

							isHandle = false;
						} catch (TimeoutException ex) {
							isHandle = true;
							logger.info(this.getName()
									+ " TimeoutException,uid is: "
									+ alarmEvent.getUid());
						} catch (Exception ex) {
							if (ex.toString().contains(
									"OptimisticLockException")) {
								isHandle = true;
								logger.info(this.getName()
										+ " OptimisticLockException,uid is: "
										+ alarmEvent.getUid());
							} else {
								isHandle = false;
								logger.info(
										this.getName()
												+ " handle alarmEvent Exception,uid is: "
												+ alarmEvent.getUid(), ex);
							}
						} finally {						
							task.cancel(true);
							handle = null;
						}
					}
				}
				handlingEventCache.removeAlarmEvent(alarmEvent.getUid());
			} else {
				try {
					Thread.sleep(sleepTime);
				} catch (Exception ex) {
					logger.info(this.getName(), ex);
				}
			}
		}
	}

	@Override
	public synchronized void start() {
		logger.info("Start consume alarm,name is:" + this.getName());
		super.start();
	}

	@Override
	public void interrupt() {
		logger.info("Interrupt consume alarm,name is:" + this.getName());
		super.interrupt();
	}
}

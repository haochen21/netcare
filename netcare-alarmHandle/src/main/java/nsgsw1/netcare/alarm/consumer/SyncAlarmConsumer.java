package nsgsw1.netcare.alarm.consumer;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import nsgsw1.netcare.alarm.cache.HandlingEventCache;
import nsgsw1.netcare.alarm.handle.AlarmHandleImpl;
import nsgsw1.netcare.alarm.handle.SdhEventHandle;
import nsgsw1.netcare.alarm.jms.AlarmEventReceive;
import nsgsw1.netcare.alarm.util.GlobalContext;
import nsgsw1.netcare.model.alarm.AlarmEvent;
import nsgsw1.netcare.model.alarm.CurrAlarm;
import nsgsw1.netcare.model.alarm.constant.AlarmCategory;
import nsgsw1.netcare.model.alarm.constant.AlarmClearType;
import nsgsw1.netcare.model.alarm.constant.AlarmEventStatus;
import nsgsw1.netcare.service.AlarmService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class SyncAlarmConsumer extends Thread {

	private DelayQueue<AlarmEvent> alarmQueue;

	private int sleepTime;

	private int handleTime;

	private AlarmEventReceive alarmEventReceive;

	private HandlingEventCache handlingEventCache;

	private AlarmService alarmService;

	// 记录第一次同步的时间
	private Date firstSyncDate;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final static Logger logger = LoggerFactory
			.getLogger(SyncAlarmConsumer.class);

	public SyncAlarmConsumer() {

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

	public AlarmService getAlarmService() {
		return alarmService;
	}

	public void setAlarmService(AlarmService alarmService) {
		this.alarmService = alarmService;
	}

	public Date getFirstSyncDate() {
		return firstSyncDate;
	}

	public void setFirstSyncDate(Date firstSyncDate) {
		this.firstSyncDate = firstSyncDate;
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
				if (alarmEvent.getName().equals("alarmSyncFinish")) {
					logger.info("handle alarm sync finish event:"
							+ alarmEvent.getEmsName() + ",queue size is:"
							+ alarmQueue.size());
					while (true) {
						try {
							Thread.sleep(120 * 1000);
							if (alarmQueue.size() == 0) {
								Collection<CurrAlarm> currAlarms = alarmService
										.findAlarmsByEmsAndUpdateTime(
												alarmEvent.getDeviceName(),
												firstSyncDate);
								logger.info("sync alarm clear,emsName is:"
										+ alarmEvent.getDeviceName()
										+ ",time is:"
										+ sdf.format(firstSyncDate)
										+ ",size is:" + currAlarms.size());
								currAlarms
										.stream()
										.forEach(
												currAlarm -> {
													AlarmEvent clearAlarmEvent = new AlarmEvent();
													clearAlarmEvent
															.setUid(currAlarm
																	.getUid());
													clearAlarmEvent
															.setName(currAlarm
																	.getName());
													clearAlarmEvent
															.setCategory(AlarmCategory.SDH);
													clearAlarmEvent
															.setEmsName(currAlarm
																	.getMe()
																	.getEms()
																	.getNativeEmsName());
													clearAlarmEvent
															.setStatus(AlarmEventStatus.CLEARED);
													clearAlarmEvent
															.setMeCreateTime(new Date());
													clearAlarmEvent
															.setEmsCreateTime(new Date());
													clearAlarmEvent
															.setSyncAlarm(true);
													clearAlarmEvent
															.setClearType(AlarmClearType.SYNC);
													alarmEventReceive
															.addToSyncAlarmQueue(clearAlarmEvent);
												});
								break;
							}
						} catch (InterruptedException ex) {
							ex.printStackTrace();
							continue;
						}
					}
				}

				if (handlingEventCache.isExistInAlarmEvent(alarmEvent.getUid())) {
					if (alarmEvent.getStatus() == AlarmEventStatus.ACTIVE) {
						logger.info(this.getName()
								+ ",active alarmEvent is handling,discard it,event is: "
								+ alarmEvent.toString());
						continue;
					}
				}

				handlingEventCache.addAlarmEvent(alarmEvent.getUid());

				while (isHandle) {
					if (alarmEvent.getCategory() == AlarmCategory.SDH) {
						handle = (SdhEventHandle) GlobalContext.getInstance()
								.getApplicationContext()
								.getBean("sdhEventHandle");
					}
					if (handle != null) {
						try {
							StopWatch stopWatch = new StopWatch();
							stopWatch.start();

							handle.setAlarmEvent(alarmEvent);
							task = new FutureTask<CurrAlarm>(handle);
							Thread thread = new Thread(task);
							thread.start();
							CurrAlarm currAlarm = task.get(handleTime,
									TimeUnit.SECONDS);
							if (currAlarm != null)
								alarmEventReceive
										.dispatchToFaultQueue(currAlarm);
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
												+ " handle alarmEvent Exception,event is: "
												+ alarmEvent.toString(), ex);
							}
						} finally {
							task.cancel(true);
							handle = null;
						}
					} else {
						logger.info(this.getName() + " handle is null,uid is:"
								+ alarmEvent.getUid());
						isHandle = false;
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

package nsgsw1.netcare.cnsalarm.jms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import nsgsw1.cns.domain.alarm.AlarmEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlarmEventReceive implements MessageListener {

	private int threadNum;

	private SendNetcareAlarmEvent sendNetcareAlarmEvent;

	private BlockingQueue<AlarmEvent> alarmEventQueue;

	private List<Thread> alarmEventConsumers;

	private final static Logger logger = LoggerFactory
			.getLogger(AlarmEventReceive.class);

	public AlarmEventReceive() {

	}

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public SendNetcareAlarmEvent getSendNetcareAlarmEvent() {
		return sendNetcareAlarmEvent;
	}

	public void setSendNetcareAlarmEvent(
			SendNetcareAlarmEvent sendNetcareAlarmEvent) {
		this.sendNetcareAlarmEvent = sendNetcareAlarmEvent;
	}

	public void start() {
		alarmEventQueue = new LinkedBlockingDeque<AlarmEvent>();
		alarmEventConsumers = new ArrayList<Thread>();
		try {
			Thread.sleep(1 * 1000);
		} catch (Exception ex) {
			logger.info("fault dispatch error", ex);
		}
		// 启动故障处理类
		for (int i = 0; i < threadNum; i++) {
			NetcareAlarmEventConsumer consumer = new NetcareAlarmEventConsumer(
					sendNetcareAlarmEvent, alarmEventQueue, 500);
			consumer.setName("alarmEvent-consumer-thread-" + i);
			alarmEventConsumers.add(consumer);
			consumer.start();
		}
	}

	public void stop() {
		alarmEventQueue.clear();
		for (Thread consumer : alarmEventConsumers) {
			consumer.interrupt();
		}
	}

	@Override
	public void onMessage(Message message) {
		AlarmEvent alarmEvent = null;
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage objectMessage = (ObjectMessage) message;
				if (objectMessage.getObject() instanceof AlarmEvent) {
					alarmEvent = (AlarmEvent) objectMessage.getObject();
					alarmEventQueue.add(alarmEvent);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			alarmEvent = null;
			message = null;
		}
	}

}

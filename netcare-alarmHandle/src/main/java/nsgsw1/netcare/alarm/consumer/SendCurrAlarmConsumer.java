package nsgsw1.netcare.alarm.consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import nsgsw1.netcare.alarm.jms.SendCurrAlarmJms;
import nsgsw1.netcare.alarm.rabbitmq.SendCurrAlarmRabbitMq;
import nsgsw1.netcare.alarm.util.CustomObjectMapper;
import nsgsw1.netcare.model.alarm.CurrAlarm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SendCurrAlarmConsumer {

	private BlockingQueue<CurrAlarm> currAlarmQueue;

	private BlockingQueue<String> currAlarmJsonQueue;

	private SendCurrAlarmJms sendCurrAlarmJms;

	private SendCurrAlarmRabbitMq sendCurrAlarmRabbitMq;

	private int sleepTime;

	private final static Logger logger = LoggerFactory
			.getLogger(SendCurrAlarmConsumer.class);

	public SendCurrAlarmConsumer() {

	}

	public SendCurrAlarmJms getSendCurrAlarmJms() {
		return sendCurrAlarmJms;
	}

	public void setSendCurrAlarmJms(SendCurrAlarmJms sendCurrAlarmJms) {
		this.sendCurrAlarmJms = sendCurrAlarmJms;
	}

	public SendCurrAlarmRabbitMq getSendCurrAlarmRabbitMq() {
		return sendCurrAlarmRabbitMq;
	}

	public void setSendCurrAlarmRabbitMq(
			SendCurrAlarmRabbitMq sendCurrAlarmRabbitMq) {
		this.sendCurrAlarmRabbitMq = sendCurrAlarmRabbitMq;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	public void start() {
		currAlarmQueue = new LinkedBlockingDeque<>();
		currAlarmJsonQueue = new LinkedBlockingDeque<>();

		new Thread(() -> {
			while (true) {
				try {
					if (currAlarmQueue.size() > 0) {
						CurrAlarm currAlarm = currAlarmQueue.take();
						sendCurrAlarmJms.sendingAlarm(currAlarm);
					} else {
						Thread.sleep(sleepTime);
					}
				} catch (Exception ex) {
					logger.info("send currAlarm jms fail!", ex);
				}
			}
		}).start();

		new Thread(() -> {
			while (true) {
				try {
					if (currAlarmJsonQueue.size() > 0) {
						String alarmJson = currAlarmJsonQueue.take();
						sendCurrAlarmRabbitMq.sendingAlarm(alarmJson);
					} else {
						Thread.sleep(sleepTime);
					}
				} catch (Exception ex) {
					logger.info("send currAlarm rabbitmq fail!", ex);
				}
			}
		}).start();
	}

	public void addCurrAlarm(CurrAlarm currAlarm) {
		try {
			if (currAlarmQueue.size() < 1000)
				currAlarmQueue.put(currAlarm);
			else
				logger.info("add currAlarm fail,beyond max size!");

			if (currAlarmJsonQueue.size() < 1000) {
				String json = addToJson(currAlarm);
				if (json != null) {
					currAlarmJsonQueue.put(json);
				}
			} else
				logger.info("add currAlarm json fail,beyond max size!");
		} catch (Exception ex) {
			logger.info("add currAlarm fail!", ex);
		}
	}

	private String addToJson(CurrAlarm currAlarm) {
		try {
			ObjectMapper mapper = new CustomObjectMapper();
			String currAlarmJson = mapper.writeValueAsString(currAlarm);
			return currAlarmJson;
		} catch (Exception ex) {
			logger.info("parse currAlarm json fail!", ex);
		}
		return null;
	}

}

package nsgsw1.netcare.alarm.consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import nsgsw1.netcare.alarm.jms.SendFaultJms;
import nsgsw1.netcare.alarm.rabbitmq.SendFaultRabbitMq;
import nsgsw1.netcare.alarm.util.CustomObjectMapper;
import nsgsw1.netcare.model.alarm.Fault;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SendFaultConsumer {

	private BlockingQueue<Fault> faultQueue;

	private BlockingQueue<String> faultJsonQueue;

	private SendFaultJms sendFaultJms;

	private SendFaultRabbitMq sendFaultRabbitMq;

	private int sleepTime;

	private final static Logger logger = LoggerFactory
			.getLogger(SendFaultConsumer.class);

	public SendFaultConsumer() {

	}

	public SendFaultJms getSendFaultJms() {
		return sendFaultJms;
	}

	public void setSendFaultJms(SendFaultJms sendFaultJms) {
		this.sendFaultJms = sendFaultJms;
	}

	public SendFaultRabbitMq getSendFaultRabbitMq() {
		return sendFaultRabbitMq;
	}

	public void setSendFaultRabbitMq(SendFaultRabbitMq sendFaultRabbitMq) {
		this.sendFaultRabbitMq = sendFaultRabbitMq;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	public void start() {
		faultQueue = new LinkedBlockingDeque<>();
		faultJsonQueue = new LinkedBlockingDeque<>();
		new Thread(() -> {
			while (true) {
				try {
					if (faultQueue.size() > 0) {
						Fault fault = faultQueue.take();
						sendFaultJms.sendingFault(fault);
					} else {
						Thread.sleep(sleepTime);
					}
				} catch (Exception ex) {
					logger.info("send fault fail!", ex);
				}
			}
		}).start();

		new Thread(() -> {
			while (true) {
				try {
					if (faultJsonQueue.size() > 0) {
						String faultJson = faultJsonQueue.take();
						sendFaultRabbitMq.sendingAlarm(faultJson);
					} else {
						Thread.sleep(sleepTime);
					}
				} catch (Exception ex) {
					logger.info("send fault rabbitmq fail!", ex);
				}
			}
		}).start();
	}

	public void addFault(Fault fault) {
		try {
			if (faultQueue.size() < 1000)
				faultQueue.put(fault);
			else
				logger.info("add fault fail,beyond max size!");

			if (faultJsonQueue.size() < 1000) {
				String json = addToJson(fault);
				if (json != null)
					faultJsonQueue.put(json);
			} else
				logger.info("add fault json fail,beyond max size!");
		} catch (Exception ex) {
			logger.info("add fault fail!", ex);
		}
	}

	private String addToJson(Fault fault) {
		try {
			ObjectMapper mapper = new CustomObjectMapper();
			String faultJson = mapper.writeValueAsString(fault);
			return faultJson;
		} catch (Exception ex) {
			logger.info("parse fault json fail!", ex);
		}
		return null;
	}

}

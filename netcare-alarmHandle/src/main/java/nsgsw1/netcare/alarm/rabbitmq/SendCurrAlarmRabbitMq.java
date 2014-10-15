package nsgsw1.netcare.alarm.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class SendCurrAlarmRabbitMq {

	private RabbitTemplate rabbitTemplate;

	public RabbitTemplate getRabbitTemplate() {
		return rabbitTemplate;
	}

	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public SendCurrAlarmRabbitMq() {

	}

	public void sendingAlarm(String alarmJson) {
		rabbitTemplate.convertAndSend(alarmJson);
	}
}

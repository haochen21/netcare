package nsgsw1.netcare.alarm.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class SendFaultRabbitMq {

	private RabbitTemplate rabbitTemplate;

	public RabbitTemplate getRabbitTemplate() {
		return rabbitTemplate;
	}

	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public SendFaultRabbitMq() {

	}

	public void sendingAlarm(String faultJson) {
		rabbitTemplate.convertAndSend(faultJson);
	}
}

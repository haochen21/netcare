package nsgsw1.netcare.cnsalarm.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import nsgsw1.netcare.model.alarm.AlarmEvent;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SendNetcareAlarmEvent {

	private JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void sendingAlarmEvent(final AlarmEvent alarmEvent) {
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage message = session.createObjectMessage(alarmEvent);
				message.setStringProperty("vendorName",
						alarmEvent.getVendorName());
				return message;
			}
		});
	}
}

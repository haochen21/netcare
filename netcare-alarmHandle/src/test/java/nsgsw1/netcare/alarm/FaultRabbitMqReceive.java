package nsgsw1.netcare.alarm;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class FaultRabbitMqReceive implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("received: "
					+ new String(message.getBody(), "UTF-8"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

package nsgsw1.netcare.alarm;

import nsgsw1.netcare.config.RabbitMqConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestRabbitMqReceive {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(RabbitMqConfig.class);
		ctx.refresh();
	}

}

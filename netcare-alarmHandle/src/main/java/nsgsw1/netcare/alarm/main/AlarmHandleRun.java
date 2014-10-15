package nsgsw1.netcare.alarm.main;

import nsgsw1.netcare.alarm.config.HandleConfig;
import nsgsw1.netcare.repository.config.MongoConfiguration;
import nsgsw1.netcare.service.config.ServiceConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AlarmHandleRun {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MongoConfiguration.class);
		ctx.register(ServiceConfig.class);
		ctx.register(HandleConfig.class);
		ctx.refresh();
	}
}

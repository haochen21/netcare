package nsgsw1.netcare.cnsalarm.main;

import nsgsw1.netcare.cnsalarm.config.CnsAlarmConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Run {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(CnsAlarmConfig.class);
		ctx.refresh();
	}
}

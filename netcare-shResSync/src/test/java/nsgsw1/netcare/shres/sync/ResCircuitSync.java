package nsgsw1.netcare.shres.sync;

import nsgsw1.netcare.repository.config.MongoConfiguration;
import nsgsw1.netcare.service.config.ServiceConfig;
import nsgsw1.netcare.shres.config.ShJPAConfig;
import nsgsw1.netcare.shres.config.ShResServiceConfig;
import nsgsw1.netcare.shres.config.ShResSyncConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ResCircuitSync {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(MongoConfiguration.class);
		ctx.register(ShJPAConfig.class);
		ctx.register(ServiceConfig.class);
		ctx.register(ShResServiceConfig.class);
		ctx.register(ShResSyncConfig.class);
		ctx.refresh();
		SyncCircuit syncCircuit = ctx
				.getBean(SyncCircuit.class);
		syncCircuit.sync();
	}

}

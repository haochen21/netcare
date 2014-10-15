package nsgsw1.netcare.shres.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableScheduling
@ComponentScan({ "nsgsw1.netcare.shres.sync" })
public class ShResSyncConfig {

	public ShResSyncConfig(){
		super();
	}
	
	@Bean
	public ThreadPoolTaskExecutor taskExecutor(){
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(2);
		pool.setMaxPoolSize(5);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}
}

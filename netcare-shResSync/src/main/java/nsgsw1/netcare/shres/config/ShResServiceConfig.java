package nsgsw1.netcare.shres.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "nsgsw1.netcare.shres.service" })
public class ShResServiceConfig {

	public ShResServiceConfig() {
		super();
	}
}

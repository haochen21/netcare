package nsgsw1.netcare.alarm.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class GlobalContext implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	private static GlobalContext instance = null;

	private GlobalContext() {
	}

	public static GlobalContext getInstance() {
		if (instance == null) {
			instance = new GlobalContext();
		}
		return instance;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		applicationContext = context;
	}
}

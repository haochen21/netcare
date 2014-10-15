package nsgsw1.netcare.cnsalarm.config;

import nsgsw1.netcare.cnsalarm.jms.AlarmEventReceive;
import nsgsw1.netcare.cnsalarm.jms.EmsOperateResultReceive;
import nsgsw1.netcare.cnsalarm.jms.SendEmsOperateResult;
import nsgsw1.netcare.cnsalarm.jms.SendNetcareAlarmEvent;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;

@Configuration
@PropertySource({ "classpath:/jms.properties" })
@ComponentScan({ "nsgsw1.netcare.cnsalarm.jms" })
public class CnsAlarmConfig {

	@Autowired
	private Environment env;

	public CnsAlarmConfig() {

	}

	@Bean
	public ActiveMQConnectionFactory cnsConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(env
				.getRequiredProperty("cnsBrokerURL"));
		return activeMQConnectionFactory;
	}

	@Bean
	public ActiveMQConnectionFactory netcareConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(env
				.getRequiredProperty("netcareBrokerURL"));
		return activeMQConnectionFactory;
	}

	@Bean
	public CachingConnectionFactory cnsCachingConnectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory
				.setTargetConnectionFactory(cnsConnectionFactory());
		cachingConnectionFactory.setSessionCacheSize(1);
		return cachingConnectionFactory;
	}

	@Bean
	public CachingConnectionFactory netcareCachingConnectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory
				.setTargetConnectionFactory(netcareConnectionFactory());
		cachingConnectionFactory.setSessionCacheSize(1);
		return cachingConnectionFactory;
	}

	@Bean
	public JmsTemplate netcareJmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setDefaultDestination(new ActiveMQTopic(env
				.getRequiredProperty("netcareAlarmEventDestination")));
		jmsTemplate.setConnectionFactory(netcareCachingConnectionFactory());
		jmsTemplate.setTimeToLive(Long.parseLong(env
				.getRequiredProperty("timeToLive")));
		return jmsTemplate;
	}

	@Bean
	public JmsTemplate netcareEmsOperatorTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setDefaultDestination(new ActiveMQTopic(env
				.getRequiredProperty("netcareEmsOperateResultDestination")));
		jmsTemplate.setConnectionFactory(netcareCachingConnectionFactory());
		jmsTemplate.setTimeToLive(Long.parseLong(env
				.getRequiredProperty("timeToLive")));
		return jmsTemplate;
	}

	@Bean
	public SendNetcareAlarmEvent sendNetcareAlarmEvent() {
		SendNetcareAlarmEvent sendNetcareAlarmEvent = new SendNetcareAlarmEvent();
		sendNetcareAlarmEvent.setJmsTemplate(netcareJmsTemplate());
		return sendNetcareAlarmEvent;
	}

	@Bean
	public SendEmsOperateResult sendEmsOperateResult() {
		SendEmsOperateResult sendEmsOperateResult = new SendEmsOperateResult();
		sendEmsOperateResult.setJmsTemplate(netcareEmsOperatorTemplate());
		return sendEmsOperateResult;
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public AlarmEventReceive alarmEventReceive() {
		AlarmEventReceive alarmEventReceive = new AlarmEventReceive();
		alarmEventReceive.setSendNetcareAlarmEvent(sendNetcareAlarmEvent());
		alarmEventReceive.setThreadNum(5);
		return alarmEventReceive;
	}

	@Bean
	public SimpleMessageListenerContainer faultMessageListenerContainer() {
		SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
		messageListenerContainer
				.setConnectionFactory(cnsCachingConnectionFactory());
		messageListenerContainer.setDestination(new ActiveMQTopic(env
				.getRequiredProperty("cnsAlarmEventDestination")));
		messageListenerContainer.setMessageListener(alarmEventReceive());
		messageListenerContainer.setConcurrentConsumers(1);
		return messageListenerContainer;
	}
	
	public EmsOperateResultReceive emsOperateResultReceive() {
		EmsOperateResultReceive emsOperateResultReceive = new EmsOperateResultReceive();
		emsOperateResultReceive.setSendEmsOperateResult(sendEmsOperateResult());
		return emsOperateResultReceive;
	}

	@Bean
	public SimpleMessageListenerContainer emsOperateResultMessageListenerContainer() {
		SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
		messageListenerContainer
				.setConnectionFactory(cnsCachingConnectionFactory());
		messageListenerContainer.setDestination(new ActiveMQTopic(env
				.getRequiredProperty("cnsEmsOperateResultDestination")));
		messageListenerContainer.setMessageListener(emsOperateResultReceive());
		messageListenerContainer.setConcurrentConsumers(1);
		return messageListenerContainer;
	}
}

package nsgsw1.netcare.alarm.config;

import nsgsw1.netcare.alarm.cache.EmsCache;
import nsgsw1.netcare.alarm.cache.HandlingEventCache;
import nsgsw1.netcare.alarm.consumer.SendCurrAlarmConsumer;
import nsgsw1.netcare.alarm.consumer.SendFaultConsumer;
import nsgsw1.netcare.alarm.jms.AlarmEventReceive;
import nsgsw1.netcare.alarm.jms.EmsSyncResultReceive;
import nsgsw1.netcare.alarm.jms.SendCurrAlarmJms;
import nsgsw1.netcare.alarm.jms.SendFaultJms;
import nsgsw1.netcare.alarm.rabbitmq.SendCurrAlarmRabbitMq;
import nsgsw1.netcare.alarm.rabbitmq.SendFaultRabbitMq;
import nsgsw1.netcare.alarm.util.GlobalContext;
import nsgsw1.netcare.service.AlarmService;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@PropertySource({ "classpath:/message.properties" })
@ComponentScan({ "nsgsw1.netcare.alarm.config", "nsgsw1.netcare.alarm.cache",
		"nsgsw1.netcare.alarm.clear", "nsgsw1.netcare.alarm.handle",
		"nsgsw1.netcare.alarm.jms" })
public class HandleConfig {

	@Autowired
	private Environment env;

	@Autowired
	AlarmService alarmService;;

	public HandleConfig() {

	}

	@Bean
	public GlobalContext globalContext() {
		return GlobalContext.getInstance();
	}

	@Bean(initMethod = "start")
	public HandlingEventCache handlingEventCache() {
		HandlingEventCache handlingEventCache = new HandlingEventCache();
		return handlingEventCache;
	}

	@Bean(initMethod = "start")
	public EmsCache emsCache() {
		return new EmsCache();
	}

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(env
				.getRequiredProperty("brokerURL"));
		return activeMQConnectionFactory;
	}

	@Bean
	public CachingConnectionFactory cachingConnectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory
				.setTargetConnectionFactory(connectionFactory());
		cachingConnectionFactory.setSessionCacheSize(1);
		return cachingConnectionFactory;
	}

	@Bean
	public JmsTemplate currAlarmTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setDefaultDestination(new ActiveMQTopic(env
				.getRequiredProperty("currAlarmEventDestination")));
		jmsTemplate.setConnectionFactory(cachingConnectionFactory());
		jmsTemplate.setTimeToLive(Long.parseLong(env
				.getRequiredProperty("timeToLive")));
		return jmsTemplate;
	}

	@Bean
	public SendCurrAlarmJms sendCurrAlarmJms() {
		SendCurrAlarmJms sendCurrAlarm = new SendCurrAlarmJms();
		sendCurrAlarm.setJmsTemplate(currAlarmTemplate());
		return sendCurrAlarm;
	}

	@Bean
	public JmsTemplate faultTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setDefaultDestination(new ActiveMQTopic(env
				.getRequiredProperty("faultEventDestination")));
		jmsTemplate.setConnectionFactory(cachingConnectionFactory());
		jmsTemplate.setTimeToLive(Long.parseLong(env
				.getRequiredProperty("timeToLive")));
		return jmsTemplate;
	}

	@Bean
	public SendFaultJms sendFaultJms() {
		SendFaultJms sendFault = new SendFaultJms();
		sendFault.setJmsTemplate(faultTemplate());
		return sendFault;
	}

	@Bean
	public ConnectionFactory rabbitMqConnectionFactory() {
		org.springframework.amqp.rabbit.connection.CachingConnectionFactory connectionFactory = new org.springframework.amqp.rabbit.connection.CachingConnectionFactory();
		connectionFactory.setUsername(env
				.getRequiredProperty("rabbitMq_userName"));
		connectionFactory.setPassword(env
				.getRequiredProperty("rabbitMq_password"));
		connectionFactory.setVirtualHost(env
				.getRequiredProperty("rabbitMq_vHost"));
		connectionFactory.setHost(env.getRequiredProperty("rabbitMq_host"));
		connectionFactory.setPort(Integer.parseInt(env
				.getRequiredProperty("rabbitMq_port")));
		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(rabbitMqConnectionFactory());
	}

	@Bean
	public FanoutExchange currAlarmFanoutExchange() {
		FanoutExchange fanoutExchange = new FanoutExchange(
				env.getRequiredProperty("rabbitMq_currAlarmExchange"), true,
				false);
		return fanoutExchange;
	}

	@Bean
	public FanoutExchange faultFanoutExchange() {
		FanoutExchange fanoutExchange = new FanoutExchange(
				env.getRequiredProperty("rabbitMq_faultExchange"), true, false);
		return fanoutExchange;
	}

	@Bean
	public RabbitTemplate rabbitCurrAlarmTemplate() {
		RabbitTemplate template = new RabbitTemplate(
				rabbitMqConnectionFactory());
		template.setExchange(env
				.getRequiredProperty("rabbitMq_currAlarmExchange"));
		template.setEncoding("UTF-8");
		template.setRoutingKey("");

		RetryTemplate retryTemplate = new RetryTemplate();
		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setInitialInterval(500);
		backOffPolicy.setMultiplier(10.0);
		backOffPolicy.setMaxInterval(10000);
		retryTemplate.setBackOffPolicy(backOffPolicy);

		template.setRetryTemplate(retryTemplate);
		return template;
	}

	@Bean
	public RabbitTemplate rabbitFaultTemplate() {
		RabbitTemplate template = new RabbitTemplate(
				rabbitMqConnectionFactory());
		template.setExchange(env.getRequiredProperty("rabbitMq_faultExchange"));
		template.setEncoding("UTF-8");
		template.setRoutingKey("");

		RetryTemplate retryTemplate = new RetryTemplate();
		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setInitialInterval(500);
		backOffPolicy.setMultiplier(10.0);
		backOffPolicy.setMaxInterval(10000);
		retryTemplate.setBackOffPolicy(backOffPolicy);
		template.setRetryTemplate(retryTemplate);

		return template;
	}

	@Bean
	public SendCurrAlarmRabbitMq sendCurrAlarmRabbitMq() {
		SendCurrAlarmRabbitMq sendCurrAlarmRabbitMq = new SendCurrAlarmRabbitMq();
		sendCurrAlarmRabbitMq.setRabbitTemplate(rabbitCurrAlarmTemplate());
		return sendCurrAlarmRabbitMq;
	}

	@Bean
	public SendFaultRabbitMq sendFaultRabbitMq() {
		SendFaultRabbitMq sendFaultRabbitMq = new SendFaultRabbitMq();
		sendFaultRabbitMq.setRabbitTemplate(rabbitFaultTemplate());
		return sendFaultRabbitMq;
	}

	@Bean(initMethod = "start")
	public SendCurrAlarmConsumer sendCurrAlarmConsumer() {
		SendCurrAlarmConsumer consumer = new SendCurrAlarmConsumer();
		consumer.setSendCurrAlarmJms(sendCurrAlarmJms());
		consumer.setSendCurrAlarmRabbitMq(sendCurrAlarmRabbitMq());
		consumer.setSleepTime(500);
		return consumer;
	}

	@Bean(initMethod = "start")
	public SendFaultConsumer sendFaultConsumer() {
		SendFaultConsumer consumer = new SendFaultConsumer();
		consumer.setSendFaultJms(sendFaultJms());
		consumer.setSendFaultRabbitMq(sendFaultRabbitMq());
		consumer.setSleepTime(500);
		return consumer;
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public AlarmEventReceive alarmEventReceive() {
		AlarmEventReceive alarmEventReceive = new AlarmEventReceive();
		alarmEventReceive.setAlarmEventThreadNum(20);
		alarmEventReceive.setSyncAlarmThreadNum(20);
		alarmEventReceive.setFaultThreadNum(2);
		alarmEventReceive.setMaxAlarmQueueNum(100000);
		alarmEventReceive.setSleepTime(500);
		alarmEventReceive.setHandleTime(300);
		alarmEventReceive.setClearDelayTime(4);
		alarmEventReceive.setFaultDelayTime(0);
		alarmEventReceive.setHandlingEventCache(handlingEventCache());
		alarmEventReceive.setAlarmService(alarmService);
		return alarmEventReceive;
	}

	@Bean
	public SimpleMessageListenerContainer alarmEventMessageListenerContainer() {
		SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
		messageListenerContainer
				.setConnectionFactory(cachingConnectionFactory());
		messageListenerContainer.setDestination(new ActiveMQTopic(env
				.getRequiredProperty("alarmEventDestination")));
		messageListenerContainer.setMessageListener(alarmEventReceive());
		messageListenerContainer.setConcurrentConsumers(1);
		return messageListenerContainer;
	}

	public EmsSyncResultReceive emsSyncResultReceive() {
		EmsSyncResultReceive emsSyncResultReceive = new EmsSyncResultReceive();
		emsSyncResultReceive.setAlarmEventReceive(alarmEventReceive());
		emsSyncResultReceive.setEmsCache(emsCache());
		return emsSyncResultReceive;
	}

	@Bean
	public SimpleMessageListenerContainer emsSyncResultMessageListenerContainer() {
		SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
		messageListenerContainer
				.setConnectionFactory(cachingConnectionFactory());
		messageListenerContainer.setDestination(new ActiveMQTopic(env
				.getRequiredProperty("emsOperateResultDestination")));
		messageListenerContainer.setMessageListener(emsSyncResultReceive());
		messageListenerContainer.setConcurrentConsumers(1);
		return messageListenerContainer;
	}
}

package nsgsw1.netcare.config;

import nsgsw1.netcare.alarm.FaultRabbitMqReceive;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource({ "classpath:/message.properties" })
public class RabbitMqConfig {

	@Autowired
	private Environment env;

	public RabbitMqConfig() {

	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
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
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public Queue queue() {
		Queue queue = new Queue("fault-1",false,true,true);
		return queue;
	}

	@Bean
	public FanoutExchange faultFanoutExchange() {
		FanoutExchange fanoutExchange = new FanoutExchange(
				env.getRequiredProperty("rabbitMq_faultExchange"), false, true);
		return fanoutExchange;
	}

	@Bean
	public Binding binding() {
		Binding binding = BindingBuilder.bind(queue())
				.to(faultFanoutExchange());
		return binding;
	}

	@Bean
	public FaultRabbitMqReceive faultRabbitMqReceive() {
		FaultRabbitMqReceive faultRabbitMqReceive = new FaultRabbitMqReceive();
		return faultRabbitMqReceive;
	}

	@Bean
	public SimpleMessageListenerContainer simpleMessageListenerContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setQueues(queue());
		container.setMessageListener(faultRabbitMqReceive());
		return container;
	}
}

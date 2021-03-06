package com.example.algorithm.demo.Service.Configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@EnableJms
@ComponentScan(basePackages = "com.developerstack")
public class JmsConfig {

	String BROKER_URL = "tcp://localhost:61616"; 
	String BROKER_USERNAME = "admin"; 
	String BROKER_PASSWORD = "admin";
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory(){
	    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
	    connectionFactory.setBrokerURL(BROKER_URL);
	    connectionFactory.setPassword(BROKER_USERNAME);
	    connectionFactory.setUserName(BROKER_PASSWORD);
	    return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate(){
	    JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		/*This config will set mode to topic
        template.setPubSubDomain(true);*/
	    return template;
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {

		//Used in case the broker is not up, so the default backOff wont keep retrying to connect
		FixedBackOff fixedBackOff = new FixedBackOff();
        fixedBackOff.setMaxAttempts(3);
		fixedBackOff.setInterval(5000);
		
	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    factory.setConnectionFactory(connectionFactory());
		factory.setConcurrency("1-1");
		factory.setBackOff(fixedBackOff);
		/*This config will set mode to topic
        factory.setPubSubDomain(true);*/
	    return factory;
	}

}
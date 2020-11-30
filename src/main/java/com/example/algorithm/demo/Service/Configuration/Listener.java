package com.example.algorithm.demo.Service.Configuration;

import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

@Component
public class Listener {
	
	private static final Logger logger = LogManager.getLogger(Listener.class);


	@Autowired
	private Producer producer;

	@JmsListener(destination = "inbound.queue")
	public void receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + jsonMessage);
		if(jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
			logger.info("ACTIVEMQ MESSAGE: {}", messageData);
		}
		producer.sendMessage("outbound.queue", messageData);
	}

}
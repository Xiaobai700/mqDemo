package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@SpringBootApplication
public class DemoApplication {
	@Bean
	JmsListenerContainerFactory<?> myJmsListenerContainerFactory(ConnectionFactory connectionFactory){
		SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setPubSubDomain(true);
		return factory;

	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

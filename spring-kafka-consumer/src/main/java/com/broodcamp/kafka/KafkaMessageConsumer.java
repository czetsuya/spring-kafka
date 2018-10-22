package com.broodcamp.kafka;

import java.util.concurrent.CountDownLatch;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@EnableKafka
public class KafkaMessageConsumer {

	private CountDownLatch latch = new CountDownLatch(3);

	@KafkaListener(topics = "${kafka.message.topic.name}", groupId = "alpha", containerFactory = "alphaKafkaListenerContainerFactory")
	public void listenGroupFoo(String message) {
		System.out.println("Received message: " + message);
		latch.countDown();
	}

}

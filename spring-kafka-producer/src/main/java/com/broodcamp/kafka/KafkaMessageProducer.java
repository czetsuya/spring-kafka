package com.broodcamp.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaMessageProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value(value = "${kafka.message.topic.name}")
	private String topicName;

	public void sendMessage(String message) {
		kafkaTemplate.send(topicName, message);
	}

}

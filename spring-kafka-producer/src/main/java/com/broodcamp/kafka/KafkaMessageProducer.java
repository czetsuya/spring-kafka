package com.broodcamp.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.broodcamp.kafka.domain.Order;

@Component
public class KafkaMessageProducer {

	@Value(value = "${kafka.bootstrap.server}")
	private String bootstrapAddress;

	@Value(value = "${kafka.message.topic.name}")
	private String topicName;

	@Value(value = "${kafka.partitioned.topic.name}")
	private String partionedTopicName;

	@Value(value = "${kafka.filtered.topic.name}")
	private String filteredTopicName;

	@Value(value = "${kafka.orders.topic.name}")
	private String ordersTopicName;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private KafkaTemplate<String, Order> orderKafkaTemplate;

	public void sendMessage(String message) {
		kafkaTemplate.send(topicName, message);
	}

	public void sendMessageToPartion(String key, String message, int partition) {
		kafkaTemplate.send(partionedTopicName, partition, key, message);
	}

	public void sendMessageToFiltered(String message) {
		kafkaTemplate.send(filteredTopicName, message);
	}

	public void sendOrder(Order order) {
		orderKafkaTemplate.send(ordersTopicName, order);
	}

}

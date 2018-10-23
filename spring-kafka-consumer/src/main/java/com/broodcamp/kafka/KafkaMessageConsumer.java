package com.broodcamp.kafka;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.broodcamp.kafka.domain.Order;

@Component
public class KafkaMessageConsumer {

	private Logger log = LoggerFactory.getLogger(KafkaMessageConsumerConfiguration.class);

	private CountDownLatch latch = new CountDownLatch(3);

	@KafkaListener(topics = "${kafka.message.topic.name}", groupId = "alpha", containerFactory = "alphaKafkaListenerContainerFactory")
	public void listenGroupAlpha(String message) {
		log.debug("received from group=alpha, message={}", message);
		latch.countDown();
	}

	@KafkaListener(topics = "${kafka.message.topic.name}", groupId = "beta", containerFactory = "betaKafkaListenerContainerFactory")
	public void listenGroupBeta(String message) {
		log.debug("received from group=beta, message={}", message);
		latch.countDown();
	}

	@KafkaListener(topics = "${kafka.message.topic.name}", containerFactory = "charlieKafkaListenerContainerFactory")
	public void listenCharlie(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		log.debug("received from group=charlie, message={}", message);
		latch.countDown();
	}

	@KafkaListener(topicPartitions = @TopicPartition(topic = "${kafka.partitioned.topic.name}", partitions = { "0",
			"3" }))
	public void listenToParition(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		log.debug("received from group=delta, partition={}, message={}", partition, message);
		latch.countDown();
	}

	@KafkaListener(topics = "${kafka.filtered.topic.name}", containerFactory = "echoKafkaListenerContainerFactory")
	public void listenWithFilter(String message) {
		log.debug("received from group=echo, message={}", message);
		latch.countDown();
	}

	@KafkaListener(topics = "${kafka.orders.topic.name}", containerFactory = "foxtrotKafkaListenerContainerFactory")
	public void listenToOrder(Order order) {
		log.debug("received order={}", order);
		latch.countDown();
	}

}

package com.broodcamp.kafka;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.broodcamp.kafka.domain.Order;

@Component
public class SingletonApp {

	private Logger log = LogManager.getLogger(SingletonApp.class);

	@Autowired
	private KafkaMessageProducer kafkaMessageProducer;

	@PostConstruct
	public void init() {
		log.debug("sending message");
		kafkaMessageProducer.sendMessage("Alpha message");

		for (int i = 0; i < 5; i++) {
			kafkaMessageProducer.sendMessageToPartion("message-" + i, "Should be received in partitioned topic.", i);
		}

		kafkaMessageProducer.sendMessageToFiltered("Hello World!");
		kafkaMessageProducer.sendMessageToFiltered("echo World!");

		Order order = new Order();
		order.setAmount(new BigDecimal(100));
		order.setId(1L);
		order.setProduct("Kafka Essentials");
		order.setQuantity(1);

		kafkaMessageProducer.sendOrder(order);
	}

}

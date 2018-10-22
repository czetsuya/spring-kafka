package com.broodcamp.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringKafkaProducerApplication implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(SpringKafkaProducerApplication.class);

	@Value(value = "${kafka.bootstrap.server}")
	private String bootstrapAddress;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringKafkaProducerApplication.class, args);

//		KafkaMessageProducer kafkaMessageProducer = context.getBean(KafkaMessageProducer.class);
//		kafkaMessageProducer.sendMessage("Hello World!");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.debug("Debugging log");
		logger.info("Info log");
		logger.warn("Hey, This is a warning!");
		logger.error("Oops! We have an Error. OK");
	}

//	@Bean
//	public ProducerFactory<String, String> producerFactory() {
//		Map<String, Object> configProps = new HashMap<>();
//		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		return new DefaultKafkaProducerFactory<>(configProps);
//	}
//
//	@Bean
//	public KafkaTemplate<String, String> kafkaTemplate() {
//		return new KafkaTemplate<>(producerFactory());
//	}
//
//	@Bean
//	public KafkaMessageProducer kafkaMessageProducer() {
//		return new KafkaMessageProducer();
//	}

}

package com.broodcamp.kafka;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SingletonApp {

	private Logger log = LogManager.getLogger(SingletonApp.class);

	@PostConstruct
	public void init() {
		System.out.println("hello edi");
		log.debug("postConstruct");
	}

	@EventListener(ApplicationReadyEvent.class)
	public void eventBootup() {
		log.debug("eventBootup");
	}

}

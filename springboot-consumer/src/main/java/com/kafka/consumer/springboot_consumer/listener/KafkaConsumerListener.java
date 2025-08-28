package com.kafka.consumer.springboot_consumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumerListener {

    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @KafkaListener(topics = { "springboot-topic" }, groupId = "group-id")
    public void listener(String message) {
        LOGGER.info("Mensaje recibido: " + message);
    }
}

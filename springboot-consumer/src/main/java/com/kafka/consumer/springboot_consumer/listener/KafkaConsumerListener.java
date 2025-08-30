package com.kafka.consumer.springboot_consumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.kafka.bo.springboot_bo.model.Consent;

@Configuration
public class KafkaConsumerListener {

    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @KafkaListener(topics = { "springboot-topic" }, groupId = "group-id", containerFactory = "consumer")
    public void listener(String message) {
        LOGGER.info("Mensaje recibido: " + message);
    }

    @KafkaListener(topics = {
            "springboot-topic-consent" }, groupId = "group-id-consent", containerFactory = "consumerConsent")
    public void listenerJson(ConsumerRecord<String, Consent> consumerRecord) {
        LOGGER.info("Consent received | {} | {} | {} | {} | {}", consumerRecord.value().getReference(),
                consumerRecord.value().getType(), consumerRecord.value().getDocument(),
                consumerRecord.value().getName(), consumerRecord.value().isGranted());
    }
}

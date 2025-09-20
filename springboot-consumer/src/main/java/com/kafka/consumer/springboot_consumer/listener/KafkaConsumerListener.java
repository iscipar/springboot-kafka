package com.kafka.consumer.springboot_consumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.kafka.bo.springboot_bo.avro.Employee;
import com.kafka.bo.springboot_bo.model.Consent;
import com.kafka.consumer.springboot_consumer.jpa.entity.ConsentEntity;
import com.kafka.consumer.springboot_consumer.mapper.ConsentMapper;
import com.kafka.consumer.springboot_consumer.service.ConsentService;

@Configuration
public class KafkaConsumerListener {

    @Autowired
    private ConsentService consentService;

    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @KafkaListener(topics = { "springboot-topic" }, groupId = "group-id", containerFactory = "consumer")
    public void listener(String message) {
        LOGGER.info("Mensaje recibido: " + message);
    }

    @KafkaListener(topics = {
            "springboot-topic-consent" }, groupId = "group-id-consent", containerFactory = "consumerConsent")
    public void listenerJson(ConsumerRecord<String, Consent> consumerRecord) {
        if (!consumerRecord.value().getType().equals("INQ")) {
            throw new IllegalArgumentException("Unsupported type " + consumerRecord.value().getType());
        }
        ConsentEntity consentEntity = ConsentMapper.MAPPER.mapToConsentEntity(consumerRecord.value());
        consentService.createConsent(consentEntity);
        LOGGER.info("Consent updated | {} | {} | {} | {} | {}", consumerRecord.value().getReference(),
                consumerRecord.value().getType(), consumerRecord.value().getDocument(),
                consumerRecord.value().getName(), consumerRecord.value().isGranted());
    }

    @KafkaListener(topics = {
            "springboot-topic-employee" }, groupId = "group-id-employee", containerFactory = "consumerEmployee")
    public void listenerAvro(ConsumerRecord<String, Employee> consumerRecord) {
        LOGGER.info("Employee detail | {} | {} | {}", consumerRecord.value().getId(),
                consumerRecord.value().getName(), consumerRecord.value().getDepartment());
    }
}

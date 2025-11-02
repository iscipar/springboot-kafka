package com.kafka.producer.springboot_producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StringController {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/retryable")
    public String retryable() {
        kafkaTemplate.send("springboot-topic-retryable", "Mensaje enviado desde Spring Boot");
        return "Mensaje enviado desde Spring Boot";
    }
}
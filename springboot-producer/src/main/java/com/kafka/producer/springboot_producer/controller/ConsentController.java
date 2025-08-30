package com.kafka.producer.springboot_producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.bo.springboot_bo.model.Consent;

@RestController
@RequestMapping("/api/consent")
public class ConsentController {

    @Autowired
    KafkaTemplate<String, Consent> kafkaTemplateConsent;

    @PostMapping("/granted")
    public String consentGranted(@RequestBody Consent consent) {
        kafkaTemplateConsent.send("springboot-topic-consent", consent);
        return "Consent " + consent.getName() + " granted successfully!";
    }

    @PostMapping("/revoked")
    public String consentRevoked(@RequestBody Consent consent) {
        kafkaTemplateConsent.send("springboot-topic-consent", consent);
        return "Consent " + consent.getName() + " revoked successfully!";
    }
}

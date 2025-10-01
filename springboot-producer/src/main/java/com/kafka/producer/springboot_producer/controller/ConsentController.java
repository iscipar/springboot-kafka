package com.kafka.producer.springboot_producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
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

    @Autowired
    KafkaTemplate<String, Consent> kafkaTemplateConsentTransactional;

    @Autowired
    KafkaTransactionManager kafkaConsentTransactionManager;

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

    @PostMapping("/grantedTransactional")
    public String consentGrantedTransactional(@RequestBody Consent consent) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(kafkaConsentTransactionManager);
        transactionTemplate.execute(s -> {
            kafkaTemplateConsentTransactional.send("springboot-topic-consent-transactional", consent);
            return null;
        });
        return "Consent " + consent.getName() + " granted successfully!";
    }

    @PostMapping("/revokedTransactional")
    public String consentRevokedTransactional(@RequestBody Consent consent) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(kafkaConsentTransactionManager);
        transactionTemplate.execute(s -> {
            kafkaTemplateConsentTransactional.send("springboot-topic-consent-transactional", consent);
            return null;
        });
        return "Consent " + consent.getName() + " revoked successfully!";
    }
}

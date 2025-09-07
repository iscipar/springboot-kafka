package com.kafka.consumer.springboot_consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafka.consumer.springboot_consumer.jpa.entity.ConsentEntity;
import com.kafka.consumer.springboot_consumer.jpa.repository.ConsentRepository;

@Service
public class ConsentService {

    @Autowired
    private ConsentRepository consentRepository;

    public ConsentEntity createConsent(ConsentEntity consentEntity) {
        return consentRepository.save(consentEntity);
    }
}

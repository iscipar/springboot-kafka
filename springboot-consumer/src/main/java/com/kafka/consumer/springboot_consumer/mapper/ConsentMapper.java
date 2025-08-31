package com.kafka.consumer.springboot_consumer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.kafka.bo.springboot_bo.model.Consent;
import com.kafka.consumer.springboot_consumer.jpa.entity.ConsentEntity;

@Mapper
public interface ConsentMapper {

    ConsentMapper MAPPER = Mappers.getMapper(ConsentMapper.class);

    Consent mapToConsent(ConsentEntity consentEntity);

    ConsentEntity mapToConsentEntity(Consent consent);
}

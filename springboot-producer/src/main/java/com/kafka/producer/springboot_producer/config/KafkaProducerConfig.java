package com.kafka.producer.springboot_producer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import com.kafka.bo.springboot_bo.avro.Employee;
import com.kafka.bo.springboot_bo.model.Consent;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrapServers}")
    private String bootstrapServers;

    public Map<String, Object> producerConfig() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return properties;
    }

    public Map<String, Object> producerConfigJson() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return properties;
    }

    public Map<String, Object> producerConfigJsonTransactional() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transaction-id");
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 2);
        return properties;
    }

    public Map<String, Object> producerConfigAvro() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        properties.put("schema.registry.url", "http://localhost:8081");
        return properties;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public ProducerFactory<String, Consent> producerFactoryConsent() {
        return new DefaultKafkaProducerFactory<>(producerConfigJson());
    }

    @Bean
    public ProducerFactory<String, Consent> producerFactoryConsentTransactional() {
        return new DefaultKafkaProducerFactory<>(producerConfigJsonTransactional());
    }

    @Bean
    public ProducerFactory<String, Employee> producerFactoryEmployee() {
        return new DefaultKafkaProducerFactory<>(producerConfigAvro());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaTemplate<String, Consent> kafkaTemplateConsent(
            ProducerFactory<String, Consent> producerFactoryConsent) {
        return new KafkaTemplate<>(producerFactoryConsent);
    }

    @Bean
    public KafkaTemplate<String, Consent> kafkaTemplateConsentTransactional(
            ProducerFactory<String, Consent> producerFactoryConsentTransactional) {
        return new KafkaTemplate<>(producerFactoryConsentTransactional);
    }

    @Bean
    public KafkaTransactionManager kafkaConsentTransactionManager() {
        return new KafkaTransactionManager<>(producerFactoryConsentTransactional());
    }

    @Bean
    public KafkaTemplate<String, Employee> kafkaTemplateEmployee(
            ProducerFactory<String, Employee> producerFactoryEmployee) {
        return new KafkaTemplate<>(producerFactoryEmployee);
    }
}
package com.kafka.consumer.springboot_consumer.handler;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

import com.kafka.consumer.springboot_consumer.listener.KafkaConsumerListener;

@Service
public class GlobalErrorHandler implements CommonErrorHandler {

    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @Override
    public boolean handleOne(Exception exception, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer,
            MessageListenerContainer container) {
        LOGGER.error("Global error handler | Error message: {}", exception.getCause().getMessage());
        return true;
    }

    @Override
    public void handleOtherException(Exception exception, Consumer<?, ?> consumer, MessageListenerContainer container,
            boolean batchListener) {
        LOGGER.error("Global error handler | Error message: {}", exception.getCause().getMessage());
    }
}

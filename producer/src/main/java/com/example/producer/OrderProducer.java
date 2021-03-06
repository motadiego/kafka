package com.example.producer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderProducer {
 
    @Value("${order.topic}")
    private String orderTopic;
 
    private final KafkaTemplate kafkaTemplate;
 
    public OrderProducer(final KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
 
    public void send(final @RequestBody String order) {
        final String mensageKey = UUID.randomUUID().toString();
        log.info("Enviando mensagem ");
        kafkaTemplate.send(orderTopic, mensageKey, order);
    }
}

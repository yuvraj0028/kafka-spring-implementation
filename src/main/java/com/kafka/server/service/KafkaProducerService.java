package com.kafka.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.kafka.server.util.Constant.TOPIC;

/**
 * Service class responsible for producing messages to Kafka topics.
 */
@Slf4j
@Service
public class KafkaProducerService {

    /**
     * Autowired instance of the KafkaTemplate class.
     * This instance is used to send messages to Kafka topics.
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Sends a message to the specified Kafka topic.
     *
     * @param message the message to send
     */
    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
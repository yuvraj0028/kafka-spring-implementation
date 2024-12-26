package com.kafka.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.server.exception.KafkaProcessException;
import com.kafka.server.model.Product;
import com.kafka.server.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.kafka.server.util.Constant.PRODUCT_GROUP;
import static com.kafka.server.util.Constant.TOPIC;

/**
 * Service class responsible for consuming messages from a Kafka topic.
 */
@Slf4j
@Service
public class KafkaConsumerService {

    /**
     * Autowired instance of the ProductRepository class.
     * This instance is used to interact with the product data storage.
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * Autowired instance of the ObjectMapper class.
     * This instance is used to convert JSON messages to Product objects.
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Kafka listener method that consumes messages from the "product_saves" topic.
     * The method is part of the "product-group" consumer group.
     *
     * @param message the consumed message
     */
    @KafkaListener(topics = TOPIC, groupId = PRODUCT_GROUP)
    public void consume(String message) {
        try {
            // Convert the JSON message to a Product object using the ObjectMapper
            Product product = objectMapper.readValue(message, Product.class);
            // Save the Product object to the data storage using the ProductRepository
            productRepository.save(product);
        } catch (Exception e) {
            // Print any exceptions that occur during message processing
            log.error("Error processing message: {}", message, e);
            throw new KafkaProcessException("Error processing message: " + message);
        }
    }
}
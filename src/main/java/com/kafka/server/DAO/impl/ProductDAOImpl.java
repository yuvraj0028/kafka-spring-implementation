package com.kafka.server.DAO.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.server.DAO.ProductDAO;
import com.kafka.server.model.Product;
import com.kafka.server.repository.ProductRepository;
import com.kafka.server.service.KafkaProducerService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.kafka.server.util.Constant.PRODUCTS;

/**
 * Implementation of the ProductDAO interface.
 */
@Slf4j
@Component
public class ProductDAOImpl implements ProductDAO {

    /**
     * The repository for accessing the product data.
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * The service for sending messages to Kafka.
     */
    @Autowired
    private KafkaProducerService kafkaProducerService;

    /**
     * The object mapper for JSON serialization.
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Adds a list of products to the database.
     *
     * @param products the list of products to add
     */
    @Override
    @Transactional
    public void addProducts(List<Product> products) {
        log.info("Saving products to DB");

        // initial demo data to load in DB
        productRepository.saveAll(products);
        log.info("Products saved to DB");
    }

    /**
     * Adds a single product to the database and sends a message to Kafka.
     *
     * @param product the product to add
     * @throws JsonProcessingException if there is an error serializing the product to JSON
     */
    @Override
    @Transactional
    public void addProduct(Product product) throws JsonProcessingException {
        String productJson = objectMapper.writeValueAsString(product);

        // Send a message to Kafka for each request
        kafkaProducerService.sendMessage(productJson);
    }

    /**
     * Retrieves all products from the database.
     *
     * @return the list of products
     */
    @Override
    @Cacheable(value=PRODUCTS)
    public List<Product> getProducts() {
        log.info("Getting products from DB");
        return productRepository.findAll();
    }
}
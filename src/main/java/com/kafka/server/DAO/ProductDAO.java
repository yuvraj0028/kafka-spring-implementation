package com.kafka.server.DAO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.server.model.Product;

import java.util.List;

/**
 * Data Access Object (DAO) interface for product data.
 */
public interface ProductDAO {

    /**
     * Adds a list of products to the database.
     *
     * @param products the list of products to add
     */
    public void addProducts(List<Product> products);

    /**
     * Adds a single product to the database and sends a message to Kafka.
     *
     * @param product the product to add
     * @throws JsonProcessingException if there is an error serializing the product to JSON
     */
    public void addProduct(Product product) throws JsonProcessingException;

    /**
     * Retrieves all products from the database.
     *
     * @return the list of products
     */
    public List<Product> getProducts();
}
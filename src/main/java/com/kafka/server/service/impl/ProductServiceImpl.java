package com.kafka.server.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.server.DAO.ProductDAO;
import com.kafka.server.DTO.ProductDTO;
import com.kafka.server.exception.ProductProcessException;
import com.kafka.server.model.Product;
import com.kafka.server.service.ProductService;
import com.kafka.server.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the ProductService interface.
 * This class provides methods for adding and retrieving products.
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * Autowired instance of the ProductDAO class.
     * This instance is used to interact with the product data storage.
     */
    @Autowired
    private ProductDAO productDAO;

    /**
     * Adds a list of products to the data storage.
     *
     * @param products the list of products to add
     */
    @Override
    public void addProducts(List<ProductDTO> products) {
        // Convert the list of ProductDTO objects to a list of Product objects
        List<Product> productsList = products.stream().map(ProductDTO::toProduct).toList();
        // Add the products to the data storage using the ProductDAO instance
        productDAO.addProducts(productsList);
    }

    /**
     * Adds a single product to the data storage.
     *
     * @param product the product to add
     * @return a response object containing the added product and a success message
     */
    @Override
    public Response<Product> addProduct(ProductDTO product) {
        try {
            // Add the product to the data storage using the ProductDAO instance
            productDAO.addProduct(product.toProduct());
        } catch (JsonProcessingException e) {
            // Log the error and throw a ProductProcessException if a JsonProcessingException occurs
            log.error("Error while adding product to DB {}", e.getMessage());
            throw new ProductProcessException("Error while adding product to DB");
        }

        // Return a response object containing the added product and a success message
        return new Response<>("SUCCESS", product.toProduct(), HttpStatus.OK);
    }

    /**
     * Retrieves a list of all products from the data storage.
     *
     * @return a response object containing the list of products and a success message
     */
    @Override
    public Response<List<Product>> getProducts() {
        // Retrieve the list of products from the data storage using the ProductDAO instance
        List<Product> products = productDAO.getProducts();
        // Return a response object containing the list of products and a success message
        return new Response<>("SUCCESS", products, HttpStatus.OK);
    }
}
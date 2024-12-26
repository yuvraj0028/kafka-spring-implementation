package com.kafka.server.DB;

import com.kafka.server.DTO.ProductDTO;
import com.kafka.server.service.ProductService;
import com.kafka.server.util.HttpUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Component responsible for refreshing the database with new data from a mock server.
 */
@Component
@Slf4j
public class RefreshDB {

    /**
     * URL of the mock server to retrieve new data from.
     */
    @Value("${mock.url}")
    private String url;

    /**
     * Service for interacting with the product data.
     */
    @Autowired
    ProductService productService;

    /**
     * Initializes the database with new data from the mock server.
     * This method is called after the bean is constructed.
     */
    @PostConstruct
    public void init() {
        log.info("Started refreshing DB");

        // Make a GET request to the mock server to retrieve new data
        ResponseEntity<List<ProductDTO>> response = HttpUtils.makeHttpRequest(url, HttpMethod.GET, new ParameterizedTypeReference<List<ProductDTO>>() {});

        // Check if the response was successful
        if (response.getStatusCode().is2xxSuccessful()) {
            // Add the new data to the database
            productService.addProducts(response.getBody());
        } else {
            log.error("Failed to retrieve data from mock server: {}", response.getStatusCode());
        }
    }
}
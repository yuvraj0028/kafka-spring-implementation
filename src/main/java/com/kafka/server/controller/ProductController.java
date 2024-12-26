package com.kafka.server.controller;

import com.kafka.server.DTO.ProductDTO;
import com.kafka.server.model.Product;
import com.kafka.server.service.ProductService;
import com.kafka.server.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling product-related requests.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    /**
     * Service for interacting with product data.
     */
    @Autowired
    private ProductService productService;

    /**
     * Retrieves all products.
     *
     * @return a response containing the list of products
     */
    @GetMapping("/all")
    public Response<List<Product>> getProducts() {
        // Delegate to the productService to retrieve the products
        return productService.getProducts();
    }

    /**
     * Adds a new product.
     *
     * @param product the product to add
     * @return a response indicating the result of the addition
     */
    @PostMapping("/add")
    public Response<Product> addProducts(@RequestBody(required = true) ProductDTO product) {
        // Delegate to the productService to add the product
        return productService.addProduct(product);
    }
}
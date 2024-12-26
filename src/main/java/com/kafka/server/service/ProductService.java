package com.kafka.server.service;

import com.kafka.server.DTO.ProductDTO;
import com.kafka.server.model.Product;
import com.kafka.server.util.Response;

import java.util.List;

/**
 * Service interface for managing products.
 */
public interface ProductService {

    /**
     * Adds a list of products to the data storage.
     *
     * @param products the list of products to add
     */
    public void addProducts(List<ProductDTO> products);

    /**
     * Adds a single product to the data storage and returns the added product.
     *
     * @param product the product to add
     * @return a response object containing the added product
     */
    public Response<Product> addProduct(ProductDTO product);

    /**
     * Retrieves a list of all products from the data storage.
     *
     * @return a response object containing the list of products
     */
    public Response<List<Product>> getProducts();
}

package com.kafka.server.exception;

/**
 * Custom exception class for product processing errors.
 * This class is used to indicate that an error occurred while processing a product.
 * It extends the RuntimeException class, which is a generic exception class for unchecked exceptions.
 */
public class ProductProcessException extends RuntimeException {

    /**
     * Constructs a new ProductProcessException with the specified detail message.
     *
     * @param message the detail message
     */
    public ProductProcessException(String message) {
        super(message);
    }
}
package com.kafka.server.exception;

/**
 * Custom exception class for Kafka-related processing errors.
 */
public class KafkaProcessException extends RuntimeException {
    /**
     * Constructs a new KafkaProcessException with the specified error message.
     *
     * @param message the error message
     */
    public KafkaProcessException(String message) {
        super(message);
    }
}
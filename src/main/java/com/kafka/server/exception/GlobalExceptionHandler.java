package com.kafka.server.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kafka.server.util.Response;

/**
 * Global exception handler for the application.
 * This class handles all exceptions that are not caught by specific exception handlers.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all exceptions that are not caught by specific exception handlers.
     * Logs the error and returns a response with a failure message and an internal server error status code.
     *
     * @param ex the exception that was thrown
     * @return a response with a failure message and an internal server error status code
     */
    @ExceptionHandler(Exception.class)
    public Response<String> handleException(Exception ex) {
        log.error("An error occurred: {}", ex.getMessage());
        return new Response<>("FAILED : " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles ProductProcessException instances.
     * Returns a response with a failure message and a bad request status code.
     *
     * @param ex the ProductProcessException that was thrown
     * @return a response with a failure message and a bad request status code
     */
    @ExceptionHandler(ProductProcessException.class)
    public Response<String> handleJsonProcessingException(ProductProcessException ex) {
        return new Response<>("FAILED : " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles KafkaProcessException instances.
     * Returns a response with a failure message and a bad request status code.
     *
     * @param ex the KafkaProcessException that was thrown
     * @return a response with a failure message and a bad request status code
     */
    @ExceptionHandler(KafkaProcessException.class)
    public Response<String> handleJsonProcessingException(KafkaProcessException ex) {
        return new Response<>("FAILED : " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
package com.kafka.server.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * A generic response class that can hold data, a message, and a status code.
 * Used to standardize API responses.
 */
@Getter
@Setter
public class Response<T> {
    private String message;
    private T data;
    private HttpStatus statusCode;
    private boolean success;

    public Response() {}

    public Response(String message, T data, HttpStatus statusCode) {
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
        this.success = true;
    }

    public Response(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.success = false;
    }
}
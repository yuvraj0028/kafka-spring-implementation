package com.kafka.server.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Utility class for making HTTP requests.
 */
@Slf4j
public class HttpUtils<T> {
    /**
     * Makes an HTTP request to the specified URL using the specified HTTP method.
     *
     * @param url the URL to make the request to
     * @param httpMethod the HTTP method to use (e.g. GET, POST, PUT, DELETE)
     * @param typeReference the type of response to expect
     * @return the response from the server
     */
    public static <T> ResponseEntity<T> makeHttpRequest(String url, HttpMethod httpMethod, ParameterizedTypeReference<T> typeReference) {
        // Create a new RestTemplate instance to make the request
        RestTemplate restTemplate = new RestTemplate();

        // Create a new HttpHeaders instance to set the Accept header
        HttpHeaders headers = new HttpHeaders();
        // Set the Accept header to application/json
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Create a new HttpEntity instance to wrap the headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Log the URL being requested
        log.info("making http request to url {}", url);

        // Make the request using the RestTemplate
        ResponseEntity<T> response = restTemplate.exchange(url, httpMethod, entity, typeReference);

        // Log the response status code
        log.info("response status code {}", response.getStatusCode());

        // Return the response from the server
        return response;
    }
}
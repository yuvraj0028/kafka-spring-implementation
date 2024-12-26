package com.kafka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Yuvraj Singh
 * @date 2024-12-25
 * @version 1.0
 *
 * This is the main application class for the Kafka Server application.
 * It is a Spring Boot application that provides a RESTful API for interacting with Kafka topics.
 */
@EnableCaching
@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }
}
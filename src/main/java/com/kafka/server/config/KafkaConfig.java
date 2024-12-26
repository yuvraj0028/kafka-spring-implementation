package com.kafka.server.config;

import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for Kafka settings.
 */
@Configuration
public class KafkaConfig {

    /**
     * Creates a producer factory with the necessary configuration properties.
     *
     * @return a producer factory instance
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        // Create a map to store the configuration properties
        Map<String, Object> configProps = new HashMap<>();

        // Set the bootstrap servers for the Kafka cluster
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");

        // Set the serializer class for keys and values
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create a default Kafka producer factory with the configuration properties
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Creates a Kafka template instance with the producer factory.
     *
     * @return a Kafka template instance
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        // Create a Kafka template instance with the producer factory
        return new KafkaTemplate<>(producerFactory());
    }
}
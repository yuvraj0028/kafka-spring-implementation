package com.kafka.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import static com.kafka.server.util.Constant.ID;
import static com.kafka.server.util.Constant.RATING_ID;

/**
 * Represents a product entity.
 */
@Entity
@Getter
@Setter
@ToString
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private float price;

    @Column(length = 1000)
    private String description;

    private String category;

    @Column(nullable = false)
    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = RATING_ID, referencedColumnName = ID)
    private Rating rating;
}
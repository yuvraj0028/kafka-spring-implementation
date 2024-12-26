package com.kafka.server.DTO;

import com.kafka.server.model.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for product data.
 */
@Getter
@Setter
public class ProductDTO {
    private long id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Price is required")
    private float price;
    private String description;
    private String category;
    @NotBlank(message = "Image is required")
    private String image;
    private RatingDTO rating;

    /**
     * Converts this ProductDTO to a Product object.
     *
     * @return the Product object
     */
    public Product toProduct() {
        Product p = new Product();
        p.setTitle(title);
        p.setPrice(price);
        p.setDescription(description);
        p.setCategory(category);
        p.setImage(image);
        p.setRating(rating.toRating());
        return p;
    }
}
package com.kafka.server.DTO;

import com.kafka.server.model.Rating;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for rating data.
 */
@Getter
@Setter
public class RatingDTO {
    private float rate;
    private int count;

    /**
     * Converts this RatingDTO to a Rating object.
     *
     * @return the Rating object
     */
    public Rating toRating() {
        Rating rating = new Rating();
        rating.setRate(rate);
        rating.setCount(count);
        return rating;
    }
}
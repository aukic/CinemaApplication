package ferit.cinema.feature.review;

import lombok.Data;

@Data
public class ReviewRequest {
    private Integer rating;
    private String description;
    private Long userId;
}

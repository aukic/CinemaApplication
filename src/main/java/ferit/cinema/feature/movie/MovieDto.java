package ferit.cinema.feature.movie;

import ferit.cinema.feature.image.ImageDto;
import ferit.cinema.feature.review.ReviewDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MovieDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Integer duration_min;
    private Double currentRating;
    private List<ReviewDto> reviews;
    private List<ImageDto> images;
}

package ferit.cinema.feature.review;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ReviewDtoMapper{
    public ReviewDto map(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setDescription(review.getDescription());
        reviewDto.setRating(review.getRating());
        reviewDto.setUser(review.getUser());
        return reviewDto;
    }
}

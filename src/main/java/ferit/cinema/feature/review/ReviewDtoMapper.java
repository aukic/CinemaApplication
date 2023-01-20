package ferit.cinema.feature.review;

import ferit.cinema.feature.user.UserDtoMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ReviewDtoMapper{
    private final UserDtoMapper userDtoMapper;
    public ReviewDto map(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setDescription(review.getDescription());
        reviewDto.setRating(review.getRating());
        reviewDto.setUser(userDtoMapper.map(review.getUser()));
        return reviewDto;
    }
}

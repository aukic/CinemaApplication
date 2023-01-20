package ferit.cinema.feature.review;

import ferit.cinema.feature.user.UserDto;
import lombok.Data;


@Data
public class ReviewDto {
    private Long id;
    private Integer rating;
    private String description;
    private UserDto user;
}

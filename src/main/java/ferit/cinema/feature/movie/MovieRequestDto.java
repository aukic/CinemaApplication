package ferit.cinema.feature.movie;

import ferit.cinema.feature.image.ImageDto;
import lombok.Data;

import java.util.List;

@Data
public class MovieRequestDto {
    private String title;
    private String description;
    private Integer duration_min;
    private List<String> images;
    private String trailer;
}

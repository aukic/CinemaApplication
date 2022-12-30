package ferit.cinema.feature.movie;

import lombok.Data;

import java.io.Serializable;

@Data
public class MovieDto implements Serializable {
    private final Long id;
    private final String title;
    private final String description;
    private final Integer duration_min;
}

package ferit.cinema.feature.movieauditorium;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MovieProjectionRequest {
    private Long movieId;
    private Long auditoriumId;
    private LocalDateTime screeningTime;
}

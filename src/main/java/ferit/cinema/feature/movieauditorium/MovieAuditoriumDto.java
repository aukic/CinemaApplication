package ferit.cinema.feature.movieauditorium;

import ferit.cinema.feature.auditorium.Auditorium;
import ferit.cinema.feature.movie.Movie;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public class MovieAuditoriumDto implements Serializable {
    private final Integer id;
    private final Movie movie;
    private final Auditorium auditorium;
    private final OffsetDateTime screeningTime;
}

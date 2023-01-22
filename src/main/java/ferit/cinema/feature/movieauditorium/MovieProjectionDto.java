package ferit.cinema.feature.movieauditorium;

import ferit.cinema.feature.auditorium.Auditorium;
import ferit.cinema.feature.movie.Movie;
import ferit.cinema.feature.movie.MovieDto;
import ferit.cinema.feature.seat.SeatDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class MovieProjectionDto implements Serializable {
    private Long id;
    private MovieDto movie;
    private Auditorium auditorium;
    private LocalDateTime screeningTime;
    private List<SeatDto> seats;
}

package ferit.cinema.feature.movieauditorium;

import ferit.cinema.feature.auditorium.Auditorium;
import ferit.cinema.feature.movie.Movie;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "movie_auditorium")
public class MovieProjection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    @Column(name = "screening_time")
    private LocalDateTime screeningTime;

}
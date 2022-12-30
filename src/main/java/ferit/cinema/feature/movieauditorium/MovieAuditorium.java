package ferit.cinema.feature.movieauditorium;

import ferit.cinema.feature.auditorium.Auditorium;
import ferit.cinema.feature.movie.Movie;
import ferit.cinema.feature.seatreserved.SeatReserved;
import ferit.cinema.feature.ticket.Ticket;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "movie_auditorium")
public class MovieAuditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    @Column(name = "screening_time")
    private LocalDateTime screeningTime;

}
package ferit.cinema.feature.seatreserved;

import ferit.cinema.feature.ticket.Ticket;
import ferit.cinema.feature.movieauditorium.MovieProjection;
import ferit.cinema.feature.seat.Seat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "seat_reserved")
public class SeatReserved {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "movie_auditorium_id", nullable = false)
    private MovieProjection movieProjection;

    public SeatReserved(Seat seat, Ticket ticket, MovieProjection movieProjection){
        this.seat = seat;
        this.ticket = ticket;
        this.movieProjection = movieProjection;
    }

    public SeatReserved() {

    }
}
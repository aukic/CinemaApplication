package ferit.cinema.feature.seatreserved;

import ferit.cinema.feature.movieauditorium.MovieProjection;
import ferit.cinema.feature.seat.Seat;
import ferit.cinema.feature.ticket.Ticket;
import lombok.Data;

import java.io.Serializable;

@Data
public class SeatReservedDto implements Serializable {
    private Long id;
    private Seat seat;
    private Ticket ticket;
    private MovieProjection movieProjection;
}

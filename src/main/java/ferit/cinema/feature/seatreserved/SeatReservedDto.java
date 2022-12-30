package ferit.cinema.feature.seatreserved;

import ferit.cinema.feature.movieauditorium.MovieAuditorium;
import ferit.cinema.feature.seat.Seat;
import ferit.cinema.feature.ticket.Ticket;
import lombok.Data;

import java.io.Serializable;

@Data
public class SeatReservedDto implements Serializable {
    private final Integer id;
    private final Seat seat;
    private final Ticket ticket;
    private final MovieAuditorium movieAuditorium;
}

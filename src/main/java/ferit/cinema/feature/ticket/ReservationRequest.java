package ferit.cinema.feature.ticket;

import ferit.cinema.feature.movieauditorium.MovieProjection;
import ferit.cinema.feature.seat.SeatDto;
import ferit.cinema.feature.user.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ReservationRequest {
    private MovieProjection movieProjection;
    private BigDecimal price;
    private User user;
    private List<SeatDto> seatDto;
}

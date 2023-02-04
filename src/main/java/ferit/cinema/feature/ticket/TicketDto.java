package ferit.cinema.feature.ticket;

import ferit.cinema.feature.movieauditorium.MovieProjection;
import ferit.cinema.feature.seat.SeatDto;
import ferit.cinema.feature.user.User;
import ferit.cinema.feature.user.UserDto;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class TicketDto implements Serializable {
    private Long id;
    private MovieProjection movieProjection;
    private BigDecimal price;
    private List<SeatDto> seatDto;
    private UserDto user;
}

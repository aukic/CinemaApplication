package ferit.cinema.feature.ticket;

import ferit.cinema.feature.movieauditorium.MovieAuditorium;
import ferit.cinema.feature.user.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class TicketDto implements Serializable {
    private final Integer id;
    private final MovieAuditorium movieAuditorium;
    private final User user;
    private final String price;
}

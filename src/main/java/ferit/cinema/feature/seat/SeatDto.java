package ferit.cinema.feature.seat;

import ferit.cinema.feature.auditorium.Auditorium;
import lombok.Data;

import java.io.Serializable;

@Data
public class SeatDto implements Serializable {
    private final Integer id;
    private final Integer row;
    private final Integer number;
    private final Auditorium auditorium;
}

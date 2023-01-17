package ferit.cinema.feature.seattype;

import lombok.Data;

import java.io.Serializable;

@Data
public class SeatTypeDto implements Serializable {
    private final Long id;
    private final String type;
}

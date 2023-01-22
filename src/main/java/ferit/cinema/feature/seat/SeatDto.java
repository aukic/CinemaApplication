package ferit.cinema.feature.seat;

import ferit.cinema.feature.seattype.SeatType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SeatDto implements Serializable {
    private Long id;
    private Integer row;
    private Integer number;
    private SeatType seatType;
    private BigDecimal price;
    private Integer reserved;
}

package ferit.cinema.feature.ticket;

import ferit.cinema.feature.seat.SeatRequestDto;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ReservationRequest {
    private Long movieProjectionId;
    private BigDecimal price;
    private Long userId;
    private Double loyaltyPoints;
    private List<SeatRequestDto> seats;
}

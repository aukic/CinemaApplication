package ferit.cinema.feature.seat;

import ferit.cinema.feature.pricemodifier.PriceModifierRepository;
import ferit.cinema.feature.seatreserved.SeatReserved;
import ferit.cinema.feature.seatreserved.SeatReservedRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
@RequiredArgsConstructor
public class SeatDtoMapper {
    private final PriceModifierRepository priceModifierRepository;
    public SeatDto map(Seat seat){
        BigDecimal defaultPrice = priceModifierRepository.findById(1L).get().getModifier();
        BigDecimal vipModifier = priceModifierRepository.findById(2L).get().getModifier();
        SeatDto seatDto = new SeatDto();
        seatDto.setId(seat.getId());
        seatDto.setNumber(seat.getNumber());
        seatDto.setRow(seat.getRow());
        seatDto.setSeatType(seat.getSeatType());
        if(seat.getSeatType().getId().equals(1L)){
            seatDto.setPrice(defaultPrice);
        }else if(seat.getSeatType().getId().equals(2L)){
            seatDto.setPrice(defaultPrice.multiply(vipModifier));
        }
        return seatDto;
    }
}

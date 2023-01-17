package ferit.cinema.feature.ticket;

import ferit.cinema.feature.seat.SeatDto;
import ferit.cinema.feature.seatreserved.SeatReserved;
import ferit.cinema.feature.seatreserved.SeatReservedRepository;
import ferit.cinema.feature.user.User;
import ferit.cinema.feature.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketDtoMapper {
    @Autowired
    private SeatReservedRepository seatReservedRepository;

    public TicketDto map(Ticket ticket){
        TicketDto dto = new TicketDto();
        dto.setId(ticket.getId());
        dto.setPrice(ticket.getPrice());
        dto.setMovieProjection(ticket.getMovieProjection());

        List<SeatReserved> reservedSeats = seatReservedRepository.findAllByTicketId(ticket.getId());
        List<SeatDto> seatDtos = new ArrayList<>();
        if(!reservedSeats.isEmpty()){
            for (SeatReserved seatReserved: reservedSeats) {
                SeatDto seatDto = new SeatDto();
                seatDto.setId(seatReserved.getSeat().getId());
                seatDto.setRow(seatReserved.getSeat().getRow());
                seatDto.setNumber(seatReserved.getSeat().getNumber());
                seatDto.setSeatType(seatReserved.getSeat().getSeatType());
                seatDto.setReserved(1);
                seatDtos.add(seatDto);
            }
        }
        dto.setSeatDto(seatDtos);
        return dto;
    }
}

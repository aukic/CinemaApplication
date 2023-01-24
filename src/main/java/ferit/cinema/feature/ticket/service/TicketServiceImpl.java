package ferit.cinema.feature.ticket.service;

import ferit.cinema.feature.movieauditorium.MovieProjection;
import ferit.cinema.feature.movieauditorium.MovieProjectionRepository;
import ferit.cinema.feature.seat.*;
import ferit.cinema.feature.seatreserved.SeatReserved;
import ferit.cinema.feature.seatreserved.SeatReservedRepository;
import ferit.cinema.feature.ticket.*;
import ferit.cinema.feature.user.User;
import ferit.cinema.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{
    private final TicketRepository ticketRepository;
    private final TicketDtoMapper ticketDtoMapper;
    private final SeatReservedRepository seatReservedRepository;
    private final MovieProjectionRepository movieProjectionRepository;
    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private final SeatDtoMapper seatDtoMapper;
    @Override
    public List<TicketDto> getAllUserReservations(Long userId) {
        List<Ticket> tickets = ticketRepository.findAllByUserId(userId);
        List<TicketDto> ticketDtos = new ArrayList<>();
        for (Ticket ticket: tickets) {
            TicketDto ticketDto = ticketDtoMapper.map(ticket);
            ticketDtos.add(ticketDto);
        }
        return ticketDtos;
    }

    @Override
    public TicketDto createReservation(ReservationRequest request) {
        Ticket ticket = new Ticket();
        MovieProjection movieProjection = movieProjectionRepository.findById(request.getMovieProjectionId()).orElse(null);
        ticket.setPrice(request.getPrice());
        ticket.setMovieProjection(movieProjection);
        User user = userRepository.findById(request.getUserId()).orElse(null);
        ticket.setUser(user);
        ticketRepository.save(ticket);
        Double loyaltyPoints = request.getPrice().divide(BigDecimal.valueOf(10)).setScale(2, RoundingMode.HALF_EVEN).doubleValue() ;
        userRepository.updateLoyaltyPoints(loyaltyPoints, user.getId());
        return processSeats(request, ticket, movieProjection);
    }

    @Override
    public TicketDto updateReservation(ReservationRequest request, Long ticketId) {
        Optional<Ticket> existingTicket = ticketRepository.findById(ticketId);
        if(existingTicket.isPresent()){
            List<SeatReserved> seatReserveds = seatReservedRepository.findAllByTicketId(ticketId);
            seatReservedRepository.deleteAll(seatReserveds);
            Ticket ticket = existingTicket.get();
            ticket.setPrice(request.getPrice());
            MovieProjection movieProjection = movieProjectionRepository.findById(request.getMovieProjectionId()).orElse(null);
            ticket.setMovieProjection(movieProjection);
            ticket.setUser(userRepository.findById(request.getUserId()).orElse(null));
            ticketRepository.save(ticket);
            return processSeats(request, ticket, movieProjection);
        }
        return new TicketDto();
    }

    private TicketDto processSeats(ReservationRequest request, Ticket ticket, MovieProjection movieProjection) {
        List<SeatDto> seatDtos = new ArrayList<>();
        for (SeatRequestDto seatRequestDto: request.getSeats()) {
            Seat seat = seatRepository.findByAuditoriumIdAndRowAndNumber(request.getMovieProjectionId(), seatRequestDto.getRow(), seatRequestDto.getNumber());
            SeatReserved seatReserved = new SeatReserved(seat,ticket, movieProjection);
            seatReservedRepository.save(seatReserved);
            SeatDto seatDto = seatDtoMapper.map(seat);
            seatDto.setReserved(1);
            seatDtos.add(seatDto);
        }
        TicketDto ticketDto = ticketDtoMapper.map(ticket);
        ticketDto.setSeatDto(seatDtos);
        return ticketDto;
    }

}

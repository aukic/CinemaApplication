package ferit.cinema.feature.ticket.service;

import ferit.cinema.feature.movieauditorium.MovieProjection;
import ferit.cinema.feature.seat.Seat;
import ferit.cinema.feature.seat.SeatDto;
import ferit.cinema.feature.seatreserved.SeatReserved;
import ferit.cinema.feature.seatreserved.SeatReservedRepository;
import ferit.cinema.feature.ticket.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketDtoMapper ticketDtoMapper;
    @Autowired
    private SeatReservedRepository seatReservedRepository;
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
        MovieProjection movieProjection = request.getMovieProjection();
        ticket.setPrice(request.getPrice());
        ticket.setMovieProjection(movieProjection);
        ticket.setUser(request.getUser());
        ticketRepository.save(ticket);
        for (SeatDto seatDto: request.getSeatDto()) {
            Seat seat = new Seat(seatDto.getId());
            SeatReserved seatReserved = new SeatReserved(seat,ticket,movieProjection);
            seatReservedRepository.save(seatReserved);
        }
        TicketDto ticketDto = ticketDtoMapper.map(ticket);
        ticketDto.setSeatDto(request.getSeatDto());
        return ticketDto;
    }

    @Override
    public TicketDto updateReservation(ReservationRequest request, Long ticketId) {
        Optional<Ticket> existingTicket = ticketRepository.findById(ticketId);
        TicketDto ticketDto = new TicketDto();
        Ticket ticket = new Ticket();
        if(existingTicket.isPresent()){
            List<SeatReserved> seatReserveds = seatReservedRepository.findAllByTicketId(ticketId);
            for (SeatReserved seatReserved:seatReserveds){
                seatReservedRepository.delete(seatReserved);
            }
            ticket.setId(existingTicket.get().getId());
            ticket.setPrice(request.getPrice());
            ticket.setMovieProjection(request.getMovieProjection());
            ticket.setUser(request.getUser());
            ticketRepository.save(ticket);
            for (SeatDto seatDto: request.getSeatDto()) {
                Seat seat = new Seat(seatDto.getId());
                SeatReserved seatReserved = new SeatReserved(seat,ticket, request.getMovieProjection());
                seatReservedRepository.save(seatReserved);
            }
            ticketDto = ticketDtoMapper.map(ticket);
            ticketDto.setSeatDto(request.getSeatDto());
        }
        return ticketDto;
    }
}

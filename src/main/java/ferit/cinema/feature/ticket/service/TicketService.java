package ferit.cinema.feature.ticket.service;

import ferit.cinema.feature.ticket.ReservationRequest;
import ferit.cinema.feature.ticket.TicketDto;

import java.util.List;

public interface TicketService {
    List<TicketDto> getAllUserReservations(Long userId);
    TicketDto createReservation(ReservationRequest request);
    TicketDto updateReservation(ReservationRequest request, Long ticketId);
    List<TicketDto> findAllReservations();
}

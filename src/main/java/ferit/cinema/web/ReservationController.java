package ferit.cinema.web;

import ferit.cinema.feature.movie.Movie;
import ferit.cinema.feature.ticket.ReservationRequest;
import ferit.cinema.feature.ticket.Ticket;
import ferit.cinema.feature.ticket.TicketDto;
import ferit.cinema.feature.ticket.TicketRepository;
import ferit.cinema.feature.ticket.service.TicketServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
    private final TicketServiceImpl ticketService;
    private final TicketRepository ticketRepository;
    public ReservationController(TicketServiceImpl ticketService, TicketRepository ticketRepository) {
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/{userId}")
    public List<TicketDto> getUserReservations(@PathVariable Long userId){
        List<TicketDto> ticketDtos = new ArrayList<>();
        try{
            ticketDtos = ticketService.getAllUserReservations(userId);
        }catch (IllegalStateException e){
            throw new IllegalStateException();
        }
        return ticketDtos;
    }

    @PostMapping
    public ResponseEntity<TicketDto> saveReservation(@RequestBody ReservationRequest request){
        TicketDto savedTicket = new TicketDto();
        try {
            savedTicket = ticketService.createReservation(request);
        }catch (IllegalStateException e){

        }
        return ResponseEntity.ok().body(savedTicket);
    }

    @PutMapping("/ticket/{ticketId}")
    public ResponseEntity<TicketDto> updateMovie(@PathVariable Long ticketId, @RequestBody ReservationRequest request){
        TicketDto ticketDto = null;
        try {
            ticketDto = ticketService.updateReservation(request, ticketId);
        }catch (IllegalStateException e){
            throw new IllegalStateException();
        }
        return ResponseEntity.ok().body(ticketDto);
    }
}

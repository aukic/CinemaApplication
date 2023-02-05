package ferit.cinema.web;

import ferit.cinema.feature.movie.Movie;
import ferit.cinema.feature.ticket.ReservationRequest;
import ferit.cinema.feature.ticket.Ticket;
import ferit.cinema.feature.ticket.TicketDto;
import ferit.cinema.feature.ticket.TicketRepository;
import ferit.cinema.feature.ticket.service.TicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reservations")
@CrossOrigin
@RequiredArgsConstructor
public class ReservationController {
    private final TicketServiceImpl ticketService;

    @GetMapping
    public List<TicketDto> getAllReservations(){
        List<TicketDto> ticketDtos = new ArrayList<>();
        try{
            ticketDtos = ticketService.findAllReservations();
        }catch (IllegalStateException e){
            throw new IllegalStateException();
        }
        return ticketDtos;
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
            throw e;
        }
        return ResponseEntity.ok().body(savedTicket);
    }

    @PutMapping("/ticket/{ticketId}")
    public ResponseEntity<TicketDto> updateReservation(@PathVariable Long ticketId, @RequestBody ReservationRequest request){
        TicketDto ticketDto = null;
        try {
            ticketDto = ticketService.updateReservation(request, ticketId);
        }catch (IllegalStateException e){
            throw new IllegalStateException();
        }
        return ResponseEntity.ok().body(ticketDto);
    }
}

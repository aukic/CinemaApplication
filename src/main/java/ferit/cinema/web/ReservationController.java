package ferit.cinema.web;

import ferit.cinema.feature.ticket.ReservationRequest;
import ferit.cinema.feature.ticket.Ticket;
import ferit.cinema.feature.ticket.TicketDto;
import ferit.cinema.feature.ticket.service.TicketServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    private final TicketServiceImpl ticketService;

    public ReservationController(TicketServiceImpl ticketService) {
        this.ticketService = ticketService;
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

    @PostMapping("/reserve")
    public ResponseEntity<TicketDto> saveReservation(@RequestBody ReservationRequest request){
        TicketDto savedTicket = new TicketDto();
        try {
            savedTicket = ticketService.createReservation(request);
        }catch (IllegalStateException e){

        }
        return ResponseEntity.ok().body(savedTicket);
    }
}

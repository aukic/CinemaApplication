package ferit.cinema.web;

import ferit.cinema.feature.seat.Seat;
import ferit.cinema.feature.seat.SeatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/seats")
public class SeatController {
    private final SeatRepository seatRepository;
    public SeatController(SeatRepository seatRepository) {this.seatRepository = seatRepository;}

    @PostMapping
    public ResponseEntity<Seat> saveSeat(@RequestBody Seat seat){
        Seat savedSeat = seatRepository.save(seat);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSeat);
    }

    @GetMapping
    public List<Seat> getSeats(){
        List<Seat> seats;
        try {
            seats = seatRepository.findAll();
        }catch (Exception e){
            throw new NoSuchElementException();
        }
        return seats;
    }

    @GetMapping("/{seatId}")
    public ResponseEntity<Seat> getSeat(@PathVariable Long seatId){
        Optional<Seat> seat = seatRepository.findById(seatId);
        if(seat.isPresent()) {
            return ResponseEntity.ok(seat.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/auditorium/{auditoriumId}")
    public Optional<List<Seat>> getSeatsByAuditoriumId(@PathVariable Long auditoriumId){
        return Optional.ofNullable(seatRepository.getAllByAuditoriumId(auditoriumId));
    }
}

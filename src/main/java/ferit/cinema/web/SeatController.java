package ferit.cinema.web;

import ferit.cinema.feature.seat.Seat;
import ferit.cinema.feature.seat.SeatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/seats")
public class SeatController {
    private final SeatRepository seatRepository;
    public SeatController(SeatRepository seatRepository) {this.seatRepository = seatRepository;}

    @PostMapping("/seat")
    public ResponseEntity<Seat> saveSeat(@RequestBody Seat seat){
        Seat savedSeat = seatRepository.save(seat);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSeat);
    }

    @PostMapping
    public ResponseEntity<List<Seat>> saveSeats(@RequestBody List<Seat> seats){
        List<Seat> savedSeats = seatRepository.saveAll(seats);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSeats);
    }

    @GetMapping
    public List<Seat> getSeats(){
        return seatRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Seat> getSeat(@PathVariable Long id){
        return seatRepository.findById(id);
    }

    @GetMapping("/auditorium/{id}")
    public Optional<List<Seat>> getSeatsByAuditoriumId(@PathVariable Long id){
        return Optional.ofNullable(seatRepository.getAllByAuditoriumId(id));
    }
}

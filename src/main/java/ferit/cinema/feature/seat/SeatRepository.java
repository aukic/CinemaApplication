package ferit.cinema.feature.seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("select s from Seat s where s.auditorium.id = ?1")
    List<Seat> getAllByAuditoriumId(Long id);
}
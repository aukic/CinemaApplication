package ferit.cinema.feature.seatreserved;

import ferit.cinema.feature.seat.SeatDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatReservedRepository extends JpaRepository<SeatReserved, Integer> {
    @Query("select s from SeatReserved s where s.movieProjection.id = ?1 and s.seat.id = ?2")
    SeatReserved findByMovieProjectionIdAndSeatId(Long movieProjectionId, Long seatId);

    @Query("select s from SeatReserved s where s.ticket.id = ?1")
    List<SeatReserved> findAllByTicketId(Long ticketId);
}
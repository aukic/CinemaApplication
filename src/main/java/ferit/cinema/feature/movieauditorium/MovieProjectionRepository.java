package ferit.cinema.feature.movieauditorium;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieProjectionRepository extends JpaRepository<MovieProjection, Long> {
    @Query("select m from MovieProjection m where m.screeningTime > current_timestamp")
    List<MovieProjection> getAllByScreeningTimeIsAfterNow();

    @Query("select m from MovieProjection m where m.movie.id = ?1 and m.screeningTime > current_timestamp")
    List<MovieProjection> getAllByMovieIdAndScreeningTimeIsAfterNow(Long movieId);
}
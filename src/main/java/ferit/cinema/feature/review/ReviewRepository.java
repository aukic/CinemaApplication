package ferit.cinema.feature.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.movie.id = ?1 and r.user.id = ?2")
    Review findByMovieIdAndUserId(Long movieId, Long userId);

    @Query("select r from Review r where r.movie.id = ?1")
    List<Review> findAllByMovieId(Long movieId);
}
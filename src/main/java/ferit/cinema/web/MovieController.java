package ferit.cinema.web;

import ferit.cinema.feature.movie.Movie;
import ferit.cinema.feature.movie.MovieDto;
import ferit.cinema.feature.movie.MovieRepository;
import ferit.cinema.feature.movie.service.MovieServiceImpl;
import ferit.cinema.feature.review.ReviewDto;
import ferit.cinema.feature.review.ReviewRequest;
import ferit.cinema.feature.review.service.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {

    private final MovieServiceImpl movieService;
    private final MovieRepository movieRepository;
    private final ReviewServiceImpl reviewService;

    public MovieController(MovieServiceImpl movieService, MovieRepository movieRepository, ReviewServiceImpl reviewService) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<MovieDto> getMovies(){
        List<MovieDto> movies;
        try {
            movies = movieService.getAllMovies();
        } catch (Exception e){
            throw new NoSuchElementException();
        }
        return movies;
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){
        Movie savedMovie = movieRepository.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        Optional<Movie> existingMovie = movieRepository.findById(id);
        if(existingMovie.isPresent()){
            movie.setId(id);
            Movie updatedMovie = movieRepository.save(movie);
            return  ResponseEntity.ok(updatedMovie);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id){
        Optional<Movie> existingMovie = movieRepository.findById(id);
        if(existingMovie.isPresent()){
            movieRepository.delete(existingMovie.get());
            return  ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{movieId}/review")
    public ResponseEntity<ReviewDto> reviewMovie(@PathVariable Long movieId, @RequestBody ReviewRequest request){
        ReviewDto reviewDto = reviewService.saveReview(movieId, request);
        return ResponseEntity.ok(reviewDto);
    }
}

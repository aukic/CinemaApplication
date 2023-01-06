package ferit.cinema.web;

import ferit.cinema.feature.movie.Movie;
import ferit.cinema.feature.movie.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/movie")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies")
    public List<Movie> getMovies(){
        return movieRepository.findAll();
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
}

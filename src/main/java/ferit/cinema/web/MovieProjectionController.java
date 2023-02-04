package ferit.cinema.web;

import ferit.cinema.feature.movieauditorium.MovieProjection;
import ferit.cinema.feature.movieauditorium.MovieProjectionDto;
import ferit.cinema.feature.movieauditorium.MovieProjectionRepository;
import ferit.cinema.feature.movieauditorium.MovieProjectionRequest;
import ferit.cinema.feature.movieauditorium.service.MovieProjectionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/projections")
@CrossOrigin
public class MovieProjectionController {
    private final MovieProjectionRepository movieProjectionRepository;
    private final MovieProjectionServiceImpl movieProjectionService;
    public MovieProjectionController(MovieProjectionRepository movieProjectionRepository, MovieProjectionServiceImpl movieProjectionService) {
        this.movieProjectionRepository = movieProjectionRepository;
        this.movieProjectionService = movieProjectionService;
    }

    @GetMapping("/{projectionId}")
    public Optional<MovieProjection> getMovieProjectionById(@PathVariable Long projectionId){
        Optional<MovieProjection> movieProjection;
        try {
            movieProjection = movieProjectionRepository.findById(projectionId);
        } catch (Exception e){
            throw new NoSuchElementException();
        }
        return movieProjection;
    }

    @PostMapping
    public ResponseEntity<MovieProjection> saveMovieProjection(@RequestBody MovieProjectionRequest request){
        MovieProjection savedMovieProjection = movieProjectionService.saveMovieProjection(request);
        return ResponseEntity.ok().body(savedMovieProjection);
    }

    @GetMapping
    public List<MovieProjectionDto> getActiveProjections(){
        List<MovieProjectionDto> movieProjectionDtos = new ArrayList<>();
        try{
            movieProjectionDtos = movieProjectionService.getActiveProjections();
        } catch(Exception e){
            throw new IllegalStateException();
        }
        return movieProjectionDtos;
    }

    @GetMapping("/movie/{movieId}")
    public List<MovieProjectionDto> getProjectionsByMovieId(@PathVariable Long movieId){
        List<MovieProjectionDto> movieProjectionDtos = new ArrayList<>();
        try{
            movieProjectionDtos = movieProjectionService.getActiveProjectionsByMovie(movieId);
        } catch(Exception e){
            throw new IllegalStateException();
        }
        return movieProjectionDtos;
    }
}

package ferit.cinema.web;

import ferit.cinema.feature.movieauditorium.MovieProjection;
import ferit.cinema.feature.movieauditorium.MovieProjectionDto;
import ferit.cinema.feature.movieauditorium.MovieProjectionRepository;
import ferit.cinema.feature.movieauditorium.service.MovieProjectionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/projection")
public class MovieProjectionController {
    private final MovieProjectionRepository movieProjectionRepository;
    private final MovieProjectionServiceImpl movieProjectionService;
    public MovieProjectionController(MovieProjectionRepository movieProjectionRepository, MovieProjectionServiceImpl movieProjectionService) {
        this.movieProjectionRepository = movieProjectionRepository;
        this.movieProjectionService = movieProjectionService;
    }

    @GetMapping("/{id}")
    public Optional<MovieProjection> getMovieProjectionById(@PathVariable Long id){
        return movieProjectionRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<MovieProjection> saveMovieProjection(@RequestBody MovieProjection movieProjection){
        MovieProjection savedMovieProjection = movieProjectionRepository.save(movieProjection);
        return ResponseEntity.ok().body(savedMovieProjection);
    }

    @PostMapping("/projections")
    public ResponseEntity<List<MovieProjection>> saveMovieProjections(@RequestBody List<MovieProjection> movieProjections){
        List<MovieProjection> savedMovieProjections = movieProjectionRepository.saveAll(movieProjections);
        return ResponseEntity.ok().body(savedMovieProjections);
    }

    @GetMapping("/projections")
    public List<MovieProjectionDto> getActiveProjections(){
        List<MovieProjectionDto> movieProjectionDtos = new ArrayList<>();
        try{
            movieProjectionDtos = movieProjectionService.createMovieProjectionDto();
        } catch(Exception e){
            throw new IllegalStateException();
        }
        return movieProjectionDtos;
    }
}

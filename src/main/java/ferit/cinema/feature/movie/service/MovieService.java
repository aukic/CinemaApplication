package ferit.cinema.feature.movie.service;

import ferit.cinema.feature.movie.Movie;
import ferit.cinema.feature.movie.MovieDto;
import ferit.cinema.feature.movie.MovieRequestDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getAllMovies();
    MovieDto getMovieById(Long movieId);
    MovieRequestDto saveMovie(MovieRequestDto movie);
}

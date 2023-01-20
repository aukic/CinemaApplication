package ferit.cinema.feature.movie.service;

import ferit.cinema.feature.movie.Movie;
import ferit.cinema.feature.movie.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getAllMovies();
    void saveMovie(Movie movie);
}

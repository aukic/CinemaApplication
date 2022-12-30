package ferit.cinema.feature.movie.service;

import ferit.cinema.feature.movie.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    void saveMovie(Movie movie);
}

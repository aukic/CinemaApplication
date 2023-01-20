package ferit.cinema.feature.movie.service;

import ferit.cinema.feature.movie.Movie;
import ferit.cinema.feature.movie.MovieDto;
import ferit.cinema.feature.movie.MovieDtoMapper;
import ferit.cinema.feature.movie.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;
    private final MovieDtoMapper movieDtoMapper;
    public MovieServiceImpl(MovieRepository movieRepository, MovieDtoMapper movieDtoMapper) {
        this.movieRepository = movieRepository;
        this.movieDtoMapper = movieDtoMapper;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<MovieDto> dtos = new ArrayList<>();
        List<Movie> movies = movieRepository.findAll();
        for(Movie movie: movies){
            dtos.add(movieDtoMapper.map(movie));
        }
        return dtos;
    }

    @Override
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }
}

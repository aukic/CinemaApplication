package ferit.cinema.feature.movie.service;

import ferit.cinema.feature.image.Image;
import ferit.cinema.feature.image.ImageDto;
import ferit.cinema.feature.image.ImageRepository;
import ferit.cinema.feature.movie.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;
    private final MovieDtoMapper movieDtoMapper;
    private final ImageRepository imageRepository;

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
    public MovieDto getMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if(Objects.nonNull(movie)){
            MovieDto dto = movieDtoMapper.map(movie);
            return dto;
        }
        return null;
    }

    @Override
    public MovieRequestDto saveMovie(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setDescription(movieRequestDto.getDescription());
        movie.setDuration_min(movieRequestDto.getDuration_min());
        movie.setTitle(movieRequestDto.getTitle());
        movie.setTrailer(movieRequestDto.getTrailer());
        movie.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        movieRepository.save(movie);
        for (String imageDto: movieRequestDto.getImages()) {
            Image image = new Image();
            image.setUrl(imageDto);
            image.setMovie(movie);
            imageRepository.save(image);
        }
        return movieRequestDto;
    }
}

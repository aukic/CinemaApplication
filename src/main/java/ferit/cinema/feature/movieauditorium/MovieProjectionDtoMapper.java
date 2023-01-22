package ferit.cinema.feature.movieauditorium;

import ferit.cinema.feature.movie.MovieDto;
import ferit.cinema.feature.movie.MovieDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieProjectionDtoMapper {
    private final MovieDtoMapper movieDtoMapper;
    public MovieProjectionDto map(MovieProjection movieProjection){
        MovieProjectionDto dto = new MovieProjectionDto();
        dto.setMovie(movieDtoMapper.map(movieProjection.getMovie()));
        dto.setAuditorium(movieProjection.getAuditorium());
        dto.setScreeningTime(movieProjection.getScreeningTime());
        dto.setId(movieProjection.getId());

        return dto;
    }
}

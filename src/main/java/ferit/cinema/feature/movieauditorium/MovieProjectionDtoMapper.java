package ferit.cinema.feature.movieauditorium;

import org.springframework.stereotype.Component;

@Component
public class MovieProjectionDtoMapper {
    public MovieProjectionDto map(MovieProjection movieProjection){
        MovieProjectionDto dto = new MovieProjectionDto();
        dto.setMovie(movieProjection.getMovie());
        dto.setAuditorium(movieProjection.getAuditorium());
        dto.setScreeningTime(movieProjection.getScreeningTime());
        dto.setId(movieProjection.getId());

        return dto;
    }
}

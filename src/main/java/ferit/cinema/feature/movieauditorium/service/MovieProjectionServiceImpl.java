package ferit.cinema.feature.movieauditorium.service;

import ferit.cinema.feature.auditorium.Auditorium;
import ferit.cinema.feature.auditorium.AuditoriumRepository;
import ferit.cinema.feature.movie.Movie;
import ferit.cinema.feature.movie.MovieRepository;
import ferit.cinema.feature.movieauditorium.*;
import ferit.cinema.feature.pricemodifier.PriceModifier;
import ferit.cinema.feature.pricemodifier.PriceModifierRepository;
import ferit.cinema.feature.seat.Seat;
import ferit.cinema.feature.seat.SeatDto;
import ferit.cinema.feature.seat.SeatDtoMapper;
import ferit.cinema.feature.seat.SeatRepository;
import ferit.cinema.feature.seatreserved.SeatReserved;
import ferit.cinema.feature.seatreserved.SeatReservedRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieProjectionServiceImpl implements MovieProjectionService{
    private final MovieProjectionRepository movieProjectionRepository;
    private final SeatRepository seatRepository;
    private final SeatDtoMapper seatDtoMapper;
    private final SeatReservedRepository seatReservedRepository;
    private final MovieProjectionDtoMapper movieProjectionDtoMapper;
    private final MovieRepository movieRepository;
    private final AuditoriumRepository auditoriumRepository;
    @Override
    public List<MovieProjectionDto> getActiveProjections() {
        return movieProjectionRepository.getAllByScreeningTimeIsAfterNow().stream()
                .map(this::mapMovieProjection)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieProjectionDto> getActiveProjectionsByMovie(Long movieId) {
        return movieProjectionRepository.getAllByMovieIdAndScreeningTimeIsAfterNow(movieId).stream()
                .map(this::mapMovieProjection)
                .collect(Collectors.toList());
    }

    private MovieProjectionDto mapMovieProjection(MovieProjection movieProjection) {
        MovieProjectionDto dto = movieProjectionDtoMapper.map(movieProjection);
        List<SeatDto> seatDtos = seatRepository.getAllByAuditoriumId(movieProjection.getAuditorium().getId()).stream()
                .map(seat -> {
                    SeatDto seatDto = seatDtoMapper.map(seat);
                    seatDto.setReserved(isSeatReserved(seat, movieProjection) ? 1 : 0);
                    return seatDto;
                })
                .collect(Collectors.toList());
        dto.setSeats(seatDtos);
        return dto;
    }

    @Override
    public Boolean isSeatReserved(Seat seat, MovieProjection movieProjection){
        SeatReserved seatReserved = seatReservedRepository.findByMovieProjectionIdAndSeatId(movieProjection.getId(), seat.getId());
        if(Objects.nonNull(seatReserved)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public MovieProjection saveMovieProjection(MovieProjectionRequest request) {
        MovieProjection movieProjection = new MovieProjection();
        Movie movie = movieRepository.findById(request.getMovieId()).orElse(null);
        Auditorium auditorium = auditoriumRepository.findById(request.getAuditoriumId()).orElse(null);
        movieProjection.setMovie(movie);
        movieProjection.setAuditorium(auditorium);
        movieProjection.setScreeningTime(request.getScreeningTime());
        movieProjectionRepository.save(movieProjection);
        return movieProjection;
    }
}

package ferit.cinema.feature.movieauditorium.service;

import ferit.cinema.feature.movieauditorium.MovieProjection;
import ferit.cinema.feature.movieauditorium.MovieProjectionDto;
import ferit.cinema.feature.movieauditorium.MovieProjectionDtoMapper;
import ferit.cinema.feature.movieauditorium.MovieProjectionRepository;
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
}

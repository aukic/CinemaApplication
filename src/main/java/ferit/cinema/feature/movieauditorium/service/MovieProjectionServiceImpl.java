package ferit.cinema.feature.movieauditorium.service;

import ferit.cinema.feature.movieauditorium.MovieProjection;
import ferit.cinema.feature.movieauditorium.MovieProjectionDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MovieProjectionServiceImpl implements MovieProjectionService{
    @Autowired
    private MovieProjectionRepository movieProjectionRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private SeatDtoMapper seatDtoMapper;
    @Autowired
    private SeatReservedRepository seatReservedRepository;
    @Override
    public List<MovieProjectionDto> createMovieProjectionDto() {
        List<MovieProjectionDto> movieProjectionDtos = new ArrayList<>();
        for (MovieProjection movieProjection:  movieProjectionRepository.getAllByScreeningTimeIsAfterNow()) {
            MovieProjectionDto dto = new MovieProjectionDto();
            List<Seat> seats = seatRepository.getAllByAuditoriumId(movieProjection.getAuditorium().getId());
            List<SeatDto> seatDtos = new ArrayList<>();
            for (Seat seat: seats) {
                SeatDto seatDto = seatDtoMapper.map(seat);
                if(isSeatReserved(seat,movieProjection)){
                    seatDto.setReserved(1);
                }else{
                    seatDto.setReserved(0);
                }
                seatDtos.add(seatDto);
            }
            dto.setSeats(seatDtos);
            dto.setMovie(movieProjection.getMovie());
            dto.setAuditorium(movieProjection.getAuditorium());
            dto.setScreeningTime(movieProjection.getScreeningTime());
            dto.setId(movieProjection.getId());
            movieProjectionDtos.add(dto);
        }
        return movieProjectionDtos;
    }

    private Boolean isSeatReserved(Seat seat, MovieProjection movieProjection){
        SeatReserved seatReserved = seatReservedRepository.findByMovieProjectionIdAndSeatId(movieProjection.getId(), seat.getId());
        if(Objects.nonNull(seatReserved)){
            return true;
        }else {
            return false;
        }
    }
}

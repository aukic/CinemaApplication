package ferit.cinema.feature.movieauditorium.service;

import ferit.cinema.feature.movieauditorium.MovieProjectionDto;
import ferit.cinema.feature.seat.SeatDto;

import java.util.List;

public interface MovieProjectionService {
    List<MovieProjectionDto> createMovieProjectionDto();
}

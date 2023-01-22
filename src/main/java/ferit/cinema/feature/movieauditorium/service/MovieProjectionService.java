package ferit.cinema.feature.movieauditorium.service;

import ferit.cinema.feature.movieauditorium.MovieProjection;
import ferit.cinema.feature.movieauditorium.MovieProjectionDto;
import ferit.cinema.feature.seat.Seat;
import ferit.cinema.feature.seat.SeatDto;

import java.util.List;

public interface MovieProjectionService {
    List<MovieProjectionDto> getActiveProjections();
    List<MovieProjectionDto> getActiveProjectionsByMovie(Long movieId);
    Boolean isSeatReserved(Seat seat, MovieProjection movieProjection);
}

package ferit.cinema.feature.movie;

import ferit.cinema.feature.review.Review;
import ferit.cinema.feature.review.ReviewDto;
import ferit.cinema.feature.review.ReviewDtoMapper;
import ferit.cinema.feature.review.ReviewRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieDtoMapper {
    private final ReviewRepository reviewRepository;
    private final ReviewDtoMapper reviewDtoMapper;
    public MovieDtoMapper(ReviewRepository reviewRepository, ReviewDtoMapper reviewDtoMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewDtoMapper = reviewDtoMapper;
    }

    public MovieDto map(Movie movie){
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setDescription(movie.getDescription());
        movieDto.setDuration_min(movie.getDuration_min());
        movieDto.setTitle(movie.getTitle());
        List<Review> reviews = reviewRepository.findAllByMovieId(movie.getId());
        List<ReviewDto> reviewDtos = reviews.stream()
                .map(reviewDtoMapper::map)
                .collect(Collectors.toList());
        double rating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0);
        movieDto.setCurrentRating(rating);
        movieDto.setReviews(reviewDtos);
        return movieDto;
    }

}

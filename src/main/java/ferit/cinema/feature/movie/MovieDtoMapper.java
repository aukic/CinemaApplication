package ferit.cinema.feature.movie;

import ferit.cinema.feature.image.Image;
import ferit.cinema.feature.image.ImageDto;
import ferit.cinema.feature.image.ImageRepository;
import ferit.cinema.feature.review.Review;
import ferit.cinema.feature.review.ReviewDto;
import ferit.cinema.feature.review.ReviewDtoMapper;
import ferit.cinema.feature.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovieDtoMapper {
    private final ReviewRepository reviewRepository;
    private final ReviewDtoMapper reviewDtoMapper;
    private final ImageRepository imageRepository;

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
        List<Image> images = imageRepository.findAllByMovieId(movie.getId());
        List<ImageDto> imageDtos = images.stream()
                .map(image -> new ImageDto(image.getUrl()))
                .collect(Collectors.toList());
        movieDto.setImages(imageDtos);
        movieDto.setCurrentRating(rating);
        movieDto.setReviews(reviewDtos);
        movieDto.setTrailer(movie.getTrailer());
        return movieDto;
    }

}

package ferit.cinema.feature.review.service;

import ferit.cinema.feature.movie.Movie;
import ferit.cinema.feature.movie.MovieRepository;
import ferit.cinema.feature.review.*;
import ferit.cinema.feature.user.User;
import ferit.cinema.feature.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final ReviewDtoMapper reviewDtoMapper;
    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieRepository movieRepository, UserRepository userRepository, ReviewDtoMapper reviewDtoMapper) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.reviewDtoMapper = reviewDtoMapper;
    }

    @Override
    public ReviewDto saveReview(Long movieId, ReviewRequest request) {
        validateRating(request.getRating());
        Optional<Review> existingReview = Optional.ofNullable(reviewRepository.findByMovieIdAndUserId(movieId, request.getUserId()));
        if(existingReview.isPresent()){
            throw new IllegalStateException("User already have a review for this movie!");
        }
        Optional<Movie> movie = movieRepository.findById(movieId);
        Optional<User> user = userRepository.findById(request.getUserId());
        if(user.isPresent() && movie.isPresent()){
            Review review = new Review(request.getRating(), request.getDescription(), movie.get(), user.get());
            reviewRepository.save(review);
            ReviewDto reviewDto = reviewDtoMapper.map(review);
            return reviewDto;
        }else {
            throw new IllegalStateException("User od movie doesn't exist!");
        }
    }

    @Override
    public void validateRating(Integer rating) {
        if(rating < 0 || rating > 5){
            throw new IllegalArgumentException("Rating needs to be number between 0 and 5!");
        }
    }
}

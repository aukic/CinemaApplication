package ferit.cinema.feature.review.service;

import ferit.cinema.feature.review.ReviewDto;
import ferit.cinema.feature.review.ReviewRequest;


public interface ReviewService {
    ReviewDto saveReview(Long movieId, ReviewRequest request);
    void validateRating(Integer rating);
}

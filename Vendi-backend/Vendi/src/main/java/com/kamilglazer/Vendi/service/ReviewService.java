package com.kamilglazer.Vendi.service;

import com.kamilglazer.Vendi.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto addReview(String token,ReviewDto review, Long productId);
    void deleteReview(String token,Long id);
    List<ReviewDto> getAllReviews(Long productId);
    ReviewDto getReviewById(Long id);
}

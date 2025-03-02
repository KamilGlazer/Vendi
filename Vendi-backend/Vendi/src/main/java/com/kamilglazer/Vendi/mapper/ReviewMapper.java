package com.kamilglazer.Vendi.mapper;

import com.kamilglazer.Vendi.dto.ReviewDto;
import com.kamilglazer.Vendi.model.Product;
import com.kamilglazer.Vendi.model.Review;
import com.kamilglazer.Vendi.model.User;

import java.time.LocalDateTime;

public class ReviewMapper {

    public static ReviewDto toDto(Review review) {
        return BaseMapper.returnNullIfNull(review) == null ? null : ReviewDto.builder()
                .reviewText(review.getReviewText())
                .rating(review.getRating())
                .productImages(review.getProductImages())
                .productId(review.getProduct().getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toEntity(ReviewDto reviewDto, Product product, User user) {
        return BaseMapper.returnNullIfNull(reviewDto) == null ? null : Review.builder()
                .reviewText(reviewDto.getReviewText())
                .rating(reviewDto.getRating())
                .productImages(reviewDto.getProductImages())
                .product(product)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
    }
}

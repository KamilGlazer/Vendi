package com.kamilglazer.Vendi.service.impl;


import com.kamilglazer.Vendi.config.JwtService;
import com.kamilglazer.Vendi.domain.USER_ROLE;
import com.kamilglazer.Vendi.dto.ReviewDto;
import com.kamilglazer.Vendi.exception.ProductNotFoundException;
import com.kamilglazer.Vendi.exception.ReviewException;
import com.kamilglazer.Vendi.mapper.ReviewMapper;
import com.kamilglazer.Vendi.model.Product;
import com.kamilglazer.Vendi.model.Review;
import com.kamilglazer.Vendi.model.User;
import com.kamilglazer.Vendi.repository.ProductRepository;
import com.kamilglazer.Vendi.repository.ReviewRepository;
import com.kamilglazer.Vendi.service.ReviewService;
import com.kamilglazer.Vendi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final UserService userService;
    private final ProductRepository productRepository;
    private final JwtService jwtService;
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewDto addReview(String token, ReviewDto reviewDto, Long productId) {
        String email = jwtService.extractUsername(token);
        User user = userService.findUserByEmail(email);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        Review review = ReviewMapper.toEntity(reviewDto,product,user);
        reviewRepository.save(Objects.requireNonNull(review));
        return ReviewMapper.toDto(review);
    }

    @Override
    public void deleteReview(String token, Long id) {
        String email = jwtService.extractUsername(token);
        User user = userService.findUserByEmail(email);
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewException("Review not found"));
        if(review.getUser().equals(user)) {
            reviewRepository.delete(review);
        }else{
            if(user.getRole()== USER_ROLE.ADMIN){
                reviewRepository.delete(review);
            }else{
                throw new ReviewException("You can't delete this review");
            }
        }
    }

    @Override
    public List<ReviewDto> getAllReviews(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        List<Review> reviews = reviewRepository.findAllByProductId(productId);
        return reviews.stream().map(ReviewMapper::toDto).toList();
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewException("Review not found"));
        return ReviewMapper.toDto(review);
    }
}

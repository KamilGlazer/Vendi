package com.kamilglazer.Vendi.controller;


import com.kamilglazer.Vendi.config.JwtService;
import com.kamilglazer.Vendi.dto.ReviewDto;
import com.kamilglazer.Vendi.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final JwtService jwtService;

    @PostMapping("/addReview")
    public ResponseEntity<ReviewDto> addReview(@RequestHeader("Authorization") String authHeader, @RequestBody ReviewDto reviewDto, @RequestParam Long productId){
        String token = jwtService.getToken(authHeader);
        return ResponseEntity.ok(reviewService.addReview(token, reviewDto, productId));
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> findAllReviews(@RequestParam Long productId){
        return ResponseEntity.ok(reviewService.getAllReviews(productId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@RequestHeader("Authorization") String authHeader,@PathVariable Long id){
        String token = jwtService.getToken(authHeader);
        reviewService.deleteReview(token,id);
        return ResponseEntity.noContent().build();
    }

}

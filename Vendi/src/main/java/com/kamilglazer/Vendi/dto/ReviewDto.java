package com.kamilglazer.Vendi.dto;

import com.kamilglazer.Vendi.model.Product;
import com.kamilglazer.Vendi.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
public class ReviewDto {
    private String reviewText;
    private int rating;
    private List<String> productImages;
    private Long productId;
    private LocalDateTime createdAt;
}

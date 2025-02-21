package com.kamilglazer.Vendi.dto;

import com.kamilglazer.Vendi.model.Category;
import com.kamilglazer.Vendi.model.Review;
import com.kamilglazer.Vendi.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDto {
    private String title;
    private String description;
    private int retailPrice;
    private int salePrice;
    private int discountPercent;
    private int quantity;
    private String color;
    private List<String> images;
    private int numRatings;
    private Category category;
    private LocalDateTime createdAt;
    private String Sizes;
    private List<Review> reviews;
}

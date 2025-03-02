package com.kamilglazer.Vendi.dto;


import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class CouponDto {
    private String code;
    private double discountPercentage;
    private LocalDateTime validityStartDate;
    private LocalDateTime validityEndDate;
    private double minimumOrderValue;
    private boolean isActive;
}

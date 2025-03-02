package com.kamilglazer.Vendi.dto;

import com.kamilglazer.Vendi.model.CartItem;
import com.kamilglazer.Vendi.model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class CartDto {
    private Long userId;
    private Set<CartItemDto> cartItems;
    private double totalSalePrice;
    private int totalItem;
    private int totalRetailPrice;
    private int discount;
    private String couponCode;
}

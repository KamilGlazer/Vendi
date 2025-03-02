package com.kamilglazer.Vendi.dto;

import com.kamilglazer.Vendi.model.Cart;
import com.kamilglazer.Vendi.model.Product;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CartItemDto {
    private Long cartId;
    private Long productId;
    private String size;
    private int quantity;
}


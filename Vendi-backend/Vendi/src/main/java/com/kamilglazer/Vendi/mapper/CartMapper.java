package com.kamilglazer.Vendi.mapper;

import com.kamilglazer.Vendi.dto.CartDto;
import com.kamilglazer.Vendi.model.Cart;
import com.kamilglazer.Vendi.model.CartItem;
import com.kamilglazer.Vendi.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class CartMapper {
    public static CartDto toDto(Cart cart) {
        return BaseMapper.returnNullIfNull(cart) == null ? null : CartDto.builder()
                .userId(cart.getUser().getId())
                .cartItems(cart.getCartItems().stream()
                        .map(CartItemMapper::toDto)
                        .collect(Collectors.toSet()))
                .totalSalePrice(cart.getTotalSalePrice())
                .totalItem(cart.getTotalItem())
                .totalRetailPrice(cart.getTotalRetailPrice())
                .discount(cart.getDiscount())
                .couponCode(cart.getCouponCode())
                .build();
    }

    public static Cart toEntity(CartDto cartDto, User user, Set<CartItem> cartItems) {
        return BaseMapper.returnNullIfNull(cartDto) == null ? null : Cart.builder()
                .user(user)
                .cartItems(cartItems)
                .totalSalePrice(cartDto.getTotalSalePrice())
                .totalItem(cartDto.getTotalItem())
                .totalRetailPrice(cartDto.getTotalRetailPrice())
                .discount(cartDto.getDiscount())
                .couponCode(cartDto.getCouponCode())
                .build();
    }
}
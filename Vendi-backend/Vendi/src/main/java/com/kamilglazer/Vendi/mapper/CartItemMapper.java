package com.kamilglazer.Vendi.mapper;

import com.kamilglazer.Vendi.dto.CartItemDto;
import com.kamilglazer.Vendi.model.Cart;
import com.kamilglazer.Vendi.model.CartItem;
import com.kamilglazer.Vendi.model.Product;

public class CartItemMapper {
    public static CartItemDto toDto(CartItem cartItem) {
        return BaseMapper.returnNullIfNull(cartItem) == null ? null : CartItemDto.builder()
                .cartId(cartItem.getCart().getId())
                .productId(cartItem.getProduct().getId())
                .size(cartItem.getSize())
                .quantity(cartItem.getQuantity())
                .build();
    }

    public static CartItem toEntity(CartItemDto cartItemDto, Cart cart, Product product) {
        return BaseMapper.returnNullIfNull(cartItemDto) == null ? null : CartItem.builder()
                .cart(cart)
                .product(product)
                .size(cartItemDto.getSize())
                .quantity(cartItemDto.getQuantity())
                .retailPrice(cartItemDto.getQuantity()*product.getRetailPrice())
                .salePrice(cartItemDto.getQuantity()*product.getSalePrice())
                .build();
    }
}

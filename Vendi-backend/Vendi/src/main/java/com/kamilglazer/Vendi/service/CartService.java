package com.kamilglazer.Vendi.service;

import com.kamilglazer.Vendi.dto.CartDto;
import com.kamilglazer.Vendi.dto.CartItemDto;
import com.kamilglazer.Vendi.model.CartItem;

import java.util.List;

public interface CartService {
    CartItemDto addItemToCart(String token, CartItemDto cartItemDto);
    void deleteItemFromCart(String token, Long itemId);
    List<CartItemDto> getAllCartItems(String token);
    CartItemDto getCartItemById(String token,Long itemId);
    void clearCart(String token);
    void useCoupon(String token, String code);
    CartDto getCart(String token);
}

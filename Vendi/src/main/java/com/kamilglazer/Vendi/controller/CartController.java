package com.kamilglazer.Vendi.controller;


import com.kamilglazer.Vendi.config.JwtService;
import com.kamilglazer.Vendi.dto.CartDto;
import com.kamilglazer.Vendi.dto.CartItemDto;
import com.kamilglazer.Vendi.model.Cart;
import com.kamilglazer.Vendi.model.CartItem;
import com.kamilglazer.Vendi.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<CartItemDto> addItemToCart(@RequestHeader("Authorization") String authHeader, @RequestBody CartItemDto cartItemDto){
        String token = jwtService.getToken(authHeader);
        return ResponseEntity.ok(cartService.addItemToCart(token, cartItemDto));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItemFromCart(@RequestHeader("Authorization") String authHeader,@PathVariable Long itemId){
        String token = jwtService.getToken(authHeader);
        cartService.deleteItemFromCart(token, itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getAllCartItems(@RequestHeader("Authorization") String authHeader){
        String token = jwtService.getToken(authHeader);
        return ResponseEntity.ok(cartService.getAllCartItems(token));
    }

    @GetMapping("/cart")
    public ResponseEntity<CartDto> getCart(@RequestHeader("Authorization") String authHeader){
        String token = jwtService.getToken(authHeader);
        return ResponseEntity.ok(cartService.getCart(token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDto> getCartItemById(@RequestHeader("Authorization") String authHeader,@PathVariable Long id){
        String token = jwtService.getToken(authHeader);
        return ResponseEntity.ok(cartService.getCartItemById(token, id));
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCart(@RequestHeader("Authorization") String authHeader){
        String token = jwtService.getToken(authHeader);
        cartService.clearCart(token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/coupon")
    public ResponseEntity<Void> useCoupon(@RequestHeader("Authorization") String authHeader,@RequestParam String couponCode){
        String token = jwtService.getToken(authHeader);
        cartService.useCoupon(token, couponCode);
        return ResponseEntity.noContent().build();
    }
}

package com.kamilglazer.Vendi.service.impl;

import com.kamilglazer.Vendi.config.JwtService;
import com.kamilglazer.Vendi.dto.CartDto;
import com.kamilglazer.Vendi.dto.CartItemDto;
import com.kamilglazer.Vendi.exception.*;
import com.kamilglazer.Vendi.mapper.CartItemMapper;
import com.kamilglazer.Vendi.mapper.CartMapper;
import com.kamilglazer.Vendi.model.*;
import com.kamilglazer.Vendi.repository.*;
import com.kamilglazer.Vendi.service.CartService;
import com.kamilglazer.Vendi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final JwtService jwtService;
    private final CouponRepository couponRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public CartItemDto addItemToCart(String token, CartItemDto cartItemDto) {
        Cart cart = getUserCart(token);
        Product product = productRepository.findById(cartItemDto.getProductId()).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        CartItem cartItem;

        if(cartItemDto.getQuantity() > 0 && cartItemDto.getQuantity() <= product.getQuantity() && product.getSizes().contains(cartItemDto.getSize())) {
             cartItem = cartItemRepository.save(Objects.requireNonNull(CartItemMapper.toEntity(cartItemDto, cart, product)));
             cart.setTotalItem(cart.getTotalItem() + cartItemDto.getQuantity());
             cart.setTotalRetailPrice(cart.getTotalRetailPrice()+cartItem.getRetailPrice());
             cart.setTotalSalePrice(cart.getTotalSalePrice()+cartItem.getSalePrice());
             cartRepository.save(cart);
        }else{
            throw new CartItemException("Invalid cart item");
        }
        return CartItemMapper.toDto(cartItem);
    }

    @Override
    public void deleteItemFromCart(String token, Long itemId) {
        Cart cart = getUserCart(token);
        CartItem cartItem = cartItemRepository.findById(itemId).orElseThrow(() -> new CartItemException("Cart item not found"));
        if(cartItem.getCart().equals(cart)) {
            cartItemRepository.delete(cartItem);
            cart.setTotalItem(cart.getTotalItem() - cartItem.getQuantity());
            cart.setTotalRetailPrice(cart.getTotalRetailPrice()-cartItem.getRetailPrice());
            cart.setTotalSalePrice(cart.getTotalSalePrice()-cartItem.getSalePrice());
            cartRepository.save(cart);
        }else{
            throw new CartItemException("You can't delete this cart item");
        }
    }

    @Override
    public List<CartItemDto> getAllCartItems(String token) {
        Cart cart = getUserCart(token);
        Set<CartItem> cartItems = cart.getCartItems();
        return cartItems.stream().map(CartItemMapper::toDto).toList();
    }

    @Override
    public CartItemDto getCartItemById(String token,Long itemId) {
        Cart cart = getUserCart(token);
        Set<CartItem> cartItems = cart.getCartItems();
        CartItem item = cartItems.stream().filter(cartItem -> cartItem.getId().equals(itemId)).findFirst().orElseThrow(() -> new CartItemException("Cart item not found"));
        return CartItemMapper.toDto(item);
    }

    @Override
    public void clearCart(String token) {
        Cart cart = getUserCart(token);
        User user = userRepository.findById(cart.getUser().getId()).orElseThrow(() -> new UserNotFoundException("Cart not found"));
        user.getUsedCoupons().clear();
        cart.getCartItems().clear();
        cart.setTotalItem(0);
        cart.setTotalRetailPrice(0);
        cart.setTotalSalePrice(0);
        cart.setCouponCode(null);
        cart.setDiscount(0);
        userRepository.save(user);
        cartRepository.save(cart);
    }


    @Override
    public void useCoupon(String token, String code) {
        String email = jwtService.extractUsername(token);
        User user = userService.findUserByEmail(email);
        Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new CartNotFoundException("Cart not found"));
        Coupon coupon = couponRepository.findByCode(code).orElseThrow(() -> new CouponNotFoundException("Coupon not found"));
        if(!user.getUsedCoupons().contains(couponRepository.findByCode(code).get()) && couponRepository.findByCode(code).isPresent()) {
            cart.setCouponCode(code);
            cart.setTotalSalePrice(cart.getTotalSalePrice() - (coupon.getDiscountPercentage()/100)*(cart.getTotalSalePrice()));
            user.getUsedCoupons().add(coupon);
            userRepository.save(user);
            cartRepository.save(cart);
        }else{
            throw new CouponNotFoundException("You can't use this coupon");
        }
    }

    @Override
    public CartDto getCart(String token) {
        Cart cart = getUserCart(token);
        return CartMapper.toDto(cart);
    }

    private Cart getUserCart(String token){
        String email = jwtService.extractUsername(token);
        User user = userService.findUserByEmail(email);
        return cartRepository.findByUserId(user.getId()).orElseThrow(() -> new CartNotFoundException("Cart not found"));
    }

}

package com.kamilglazer.Vendi.repository;

import com.kamilglazer.Vendi.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}

package com.kamilglazer.Vendi.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

    private double totalSalePrice;

    private int totalItem;

    private int totalRetailPrice;

    private int discount;

    private String couponCode;
}

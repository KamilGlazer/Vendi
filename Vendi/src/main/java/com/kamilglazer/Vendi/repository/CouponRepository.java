package com.kamilglazer.Vendi.repository;

import com.kamilglazer.Vendi.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon,Long>, JpaSpecificationExecutor<Coupon> {
    Optional<Coupon> findByCode(String code);
}

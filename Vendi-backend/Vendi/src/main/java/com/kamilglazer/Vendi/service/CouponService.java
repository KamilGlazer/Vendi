package com.kamilglazer.Vendi.service;

import com.kamilglazer.Vendi.dto.CouponDto;
import com.kamilglazer.Vendi.dto.response.ApiResponse;
import com.kamilglazer.Vendi.model.Coupon;
import org.springframework.data.domain.Page;


import java.util.List;

public interface CouponService {
    CouponDto addCoupon(CouponDto couponDto);
    void deleteCoupon(Long couponId);
    CouponDto updateCoupon(Long couponId,CouponDto couponDto);
    Page<CouponDto> getAllCoupons(Integer pageNumber, Double minDiscount, Double maxDiscount, String sort);
}

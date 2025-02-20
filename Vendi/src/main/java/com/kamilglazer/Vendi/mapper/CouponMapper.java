package com.kamilglazer.Vendi.mapper;

import com.kamilglazer.Vendi.dto.CouponDto;
import com.kamilglazer.Vendi.model.Coupon;

public class CouponMapper {

    private static <T> T returnNullIfNull(T object){
        return object;
    }

    public static CouponDto toDto(Coupon coupon) {
        return returnNullIfNull(coupon) == null ? null : CouponDto.builder()
                .code(coupon.getCode())
                .discountPercentage(coupon.getDiscountPercentage())
                .validityStartDate(coupon.getValidityStartDate())
                .validityEndDate(coupon.getValidityEndDate())
                .minimumOrderValue(coupon.getMinimumOrderValue())
                .isActive(coupon.isActive())
                .build();
    }

    public static Coupon toEntity(CouponDto couponDto) {
        return returnNullIfNull(couponDto) == null ? null : Coupon.builder()
                .code(couponDto.getCode())
                .discountPercentage(couponDto.getDiscountPercentage())
                .validityStartDate(couponDto.getValidityStartDate())
                .validityEndDate(couponDto.getValidityEndDate())
                .minimumOrderValue(couponDto.getMinimumOrderValue())
                .build();
    }

}

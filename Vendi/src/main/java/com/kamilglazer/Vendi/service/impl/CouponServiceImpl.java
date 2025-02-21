package com.kamilglazer.Vendi.service.impl;

import com.kamilglazer.Vendi.dto.CouponDto;
import com.kamilglazer.Vendi.exception.CouponExistsException;
import com.kamilglazer.Vendi.exception.CouponNotFoundException;
import com.kamilglazer.Vendi.mapper.CouponMapper;
import com.kamilglazer.Vendi.model.Coupon;
import com.kamilglazer.Vendi.repository.CouponRepository;
import com.kamilglazer.Vendi.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public CouponDto addCoupon(CouponDto couponDto) {
        couponRepository.findByCode(couponDto.getCode())
                .ifPresent(coupon -> {throw new CouponExistsException("Coupon with code: " + couponDto.getCode() + " already exists");});
        Coupon coupon = CouponMapper.toEntity(couponDto);
        Objects.requireNonNull(coupon).updateIsActive();
        coupon = couponRepository.save(coupon);
        return CouponMapper.toDto(coupon);
    }

    @Override
    public void deleteCoupon(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new CouponNotFoundException("Coupon not found"));
        couponRepository.delete(coupon);
    }

    @Override
    public Page<CouponDto> getAllCoupons(Integer pageNumber, Double minDiscount, Double maxDiscount, String sort) {
        Specification<Coupon> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (minDiscount != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("discountPercentage"), minDiscount));
            }
            if (maxDiscount != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("discountPercentage"), maxDiscount));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Pageable pageable;
        if (sort != null && !sort.isEmpty()) {
            pageable = switch (sort) {
                case "discount_low" ->
                        PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.by("discountPercentage").ascending());
                case "discount_high" ->
                        PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.by("discountPercentage").descending());
                default -> PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.unsorted());
            };
        } else {
            pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.unsorted());
        }
        Page<Coupon> couponPage = couponRepository.findAll(specification,pageable);
        return couponPage.map(CouponMapper::toDto);
    }


    @Override
    public CouponDto updateCoupon(Long couponId,CouponDto couponDto) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new CouponNotFoundException("Coupon not found"));
        coupon.setCode(couponDto.getCode());
        coupon.setDiscountPercentage(couponDto.getDiscountPercentage());
        coupon.setValidityEndDate(couponDto.getValidityEndDate());
        coupon.setValidityStartDate(couponDto.getValidityStartDate());
        coupon.updateIsActive();
        return CouponMapper.toDto(couponRepository.save(coupon));
    }
}

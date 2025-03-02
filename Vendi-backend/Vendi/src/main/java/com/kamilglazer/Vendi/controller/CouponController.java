package com.kamilglazer.Vendi.controller;


import com.kamilglazer.Vendi.dto.CouponDto;
import com.kamilglazer.Vendi.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupon")
public class CouponController {

    private final CouponService couponService;


    @PostMapping
    public ResponseEntity<CouponDto> createCoupon(@RequestBody CouponDto couponDto){
        return ResponseEntity.ok(couponService.addCoupon(couponDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long id){
        couponService.deleteCoupon(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<CouponDto>> getAllCoupons(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                         @RequestParam(required = false) Double minDiscount,
                                                         @RequestParam(required = false) Double maxDiscount,
                                                         @RequestParam(required = false) String sort){
        return ResponseEntity.ok(couponService.getAllCoupons(pageNumber, minDiscount, maxDiscount, sort));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponDto> updateCoupon(@PathVariable Long id,@RequestBody CouponDto couponDto){
        return ResponseEntity.ok(couponService.updateCoupon(id,couponDto));
    }
}

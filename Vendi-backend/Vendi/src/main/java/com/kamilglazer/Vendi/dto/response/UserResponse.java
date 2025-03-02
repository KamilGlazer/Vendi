package com.kamilglazer.Vendi.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kamilglazer.Vendi.domain.ACCOUNT_STATUS;
import com.kamilglazer.Vendi.domain.USER_ROLE;
import com.kamilglazer.Vendi.model.Address;
import com.kamilglazer.Vendi.model.Coupon;
import lombok.Builder;
import lombok.Data;
import java.util.Set;

@Data
@Builder
public class UserResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String mobile;
    private USER_ROLE role;
    private Set<Address> addresses;
    private Set<Coupon> usedCoupons;
    private ACCOUNT_STATUS accountStatus;
}

package com.kamilglazer.Vendi.mapper;

import com.kamilglazer.Vendi.dto.response.UserResponse;
import com.kamilglazer.Vendi.model.User;

public class UserMapper {

    private static <T> T returnNullIfNull(T object) {
        return object;
    }

    public static UserResponse toResponse(User user) {
        return returnNullIfNull(user) == null ? null : UserResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mobile(user.getMobile())
                .role(user.getRole())
                .addresses(user.getAddresses())
                .usedCoupons(user.getUsedCoupons())
                .accountStatus(user.getAccountStatus())
                .build();
    }

    public static User toEntity(UserResponse userResponse) {
        return returnNullIfNull(userResponse) == null ? null : User.builder()
                .email(userResponse.getEmail())
                .firstName(userResponse.getFirstName())
                .lastName(userResponse.getLastName())
                .mobile(userResponse.getMobile())
                .role(userResponse.getRole())
                .addresses(userResponse.getAddresses())
                .usedCoupons(userResponse.getUsedCoupons())
                .accountStatus(userResponse.getAccountStatus())
                .build();
    }



}

package com.kamilglazer.Vendi.mapper;

import com.kamilglazer.Vendi.dto.response.UserResponse;
import com.kamilglazer.Vendi.model.User;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        return BaseMapper.returnNullIfNull(user) == null ? null : UserResponse.builder()
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
        return BaseMapper.returnNullIfNull(userResponse) == null ? null : User.builder()
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

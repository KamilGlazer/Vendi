package com.kamilglazer.Vendi.service;

import com.kamilglazer.Vendi.dto.AddressDto;
import com.kamilglazer.Vendi.dto.response.UserResponse;
import com.kamilglazer.Vendi.model.User;

public interface UserService {
    UserResponse findUserByJwtToken(String token);
    User findUserByEmail(String email);
    AddressDto addAddress(String token, AddressDto address);
    void deleteAddress(String token, Long id);
}

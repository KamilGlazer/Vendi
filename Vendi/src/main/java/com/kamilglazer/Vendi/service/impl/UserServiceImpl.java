package com.kamilglazer.Vendi.service.impl;

import com.kamilglazer.Vendi.config.JwtService;
import com.kamilglazer.Vendi.dto.AddressDto;
import com.kamilglazer.Vendi.dto.response.UserResponse;
import com.kamilglazer.Vendi.mapper.AddressMapper;
import com.kamilglazer.Vendi.mapper.UserMapper;
import com.kamilglazer.Vendi.model.Address;
import com.kamilglazer.Vendi.model.User;
import com.kamilglazer.Vendi.repository.AddressRepository;
import com.kamilglazer.Vendi.repository.UserRepository;
import com.kamilglazer.Vendi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AddressRepository addressRepository;

    @Override
    public UserResponse findUserByJwtToken(String token) {
        String email = jwtService.extractUsername(token);
        User user = this.findUserByEmail(email);
        return UserMapper.toResponse(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public AddressDto addAddress(String token, AddressDto address) {
        String email = jwtService.extractUsername(token);
        User user = this.findUserByEmail(email);
        Address savedAddress = addressRepository.save(Objects.requireNonNull(AddressMapper.toEntity(address)));
        Set<Address> addresses = user.getAddresses();
        addresses.add(savedAddress);
        user.setAddresses(addresses);
        userRepository.save(user);
        return AddressMapper.toDto(savedAddress);
    }

}

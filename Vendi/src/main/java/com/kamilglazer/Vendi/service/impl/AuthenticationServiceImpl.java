package com.kamilglazer.Vendi.service.impl;

import com.kamilglazer.Vendi.config.JwtService;
import com.kamilglazer.Vendi.dto.request.LoginRequest;
import com.kamilglazer.Vendi.dto.request.RegisterRequest;
import com.kamilglazer.Vendi.dto.response.JwtResponse;
import com.kamilglazer.Vendi.exception.UserNotFoundException;
import com.kamilglazer.Vendi.model.User;
import com.kamilglazer.Vendi.repository.UserRepository;
import com.kamilglazer.Vendi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public JwtResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .mobile(request.getMobile())
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return JwtResponse.builder()
                .jwt(jwtToken)
                .role(user.getRole())
                .message("Registration successful")
                .build();
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException("Invalid email or password"));
        var jwtToken = jwtService.generateToken(user);
        return JwtResponse.builder()
                .jwt(jwtToken)
                .role(user.getRole())
                .message("Login successful")
                .build();
    }
}

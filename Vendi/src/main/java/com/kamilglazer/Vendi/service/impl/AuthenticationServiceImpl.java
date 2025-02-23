package com.kamilglazer.Vendi.service.impl;

import com.kamilglazer.Vendi.config.JwtService;
import com.kamilglazer.Vendi.dto.request.LoginRequest;
import com.kamilglazer.Vendi.dto.request.RegisterRequest;
import com.kamilglazer.Vendi.dto.response.JwtResponse;
import com.kamilglazer.Vendi.exception.UserNotFoundException;
import com.kamilglazer.Vendi.exception.UserWithThisEmailAlreadyExists;
import com.kamilglazer.Vendi.model.Cart;
import com.kamilglazer.Vendi.model.User;
import com.kamilglazer.Vendi.repository.CartRepository;
import com.kamilglazer.Vendi.repository.UserRepository;
import com.kamilglazer.Vendi.service.AuthenticationService;
import com.kamilglazer.Vendi.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final CartRepository cartRepository;

    @Override
    public JwtResponse register(RegisterRequest request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {throw new UserWithThisEmailAlreadyExists("User with this email already exists");});

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .mobile(request.getMobile())
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        initCart(user);

        emailService.sendVerificationEmail(user.getEmail(), jwtToken);
        return JwtResponse.builder()
                .jwt(jwtToken)
                .role(user.getRole())
                .message("Registration successful")
                .build();
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }catch (Exception e){
            throw new UserNotFoundException("Invalid email or password");
        }

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException("Invalid email or password"));
        var jwtToken = jwtService.generateToken(user);
        return JwtResponse.builder()
                .jwt(jwtToken)
                .role(user.getRole())
                .message("Login successful")
                .build();
    }


    private void initCart(User user){
        Cart cart = Cart.builder()
                .user(user)
                .totalRetailPrice(0)
                .couponCode(null)
                .discount(0)
                .totalItem(0)
                .totalSalePrice(0)
                .build();
        cartRepository.save(cart);
    }
}

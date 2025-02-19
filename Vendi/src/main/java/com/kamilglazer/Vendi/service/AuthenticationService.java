package com.kamilglazer.Vendi.service;

import com.kamilglazer.Vendi.dto.request.LoginRequest;
import com.kamilglazer.Vendi.dto.request.RegisterRequest;
import com.kamilglazer.Vendi.dto.response.JwtResponse;

public interface AuthenticationService {
    JwtResponse register(RegisterRequest request);
    JwtResponse login(LoginRequest request);

}

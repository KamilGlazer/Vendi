package com.kamilglazer.Vendi.controller;


import com.kamilglazer.Vendi.dto.request.LoginRequest;
import com.kamilglazer.Vendi.dto.request.RegisterRequest;
import com.kamilglazer.Vendi.dto.response.JwtResponse;
import com.kamilglazer.Vendi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> register(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

}

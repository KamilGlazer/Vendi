package com.kamilglazer.Vendi.controller;


import com.kamilglazer.Vendi.domain.USER_ROLE;
import com.kamilglazer.Vendi.dto.request.RegisterRequest;
import com.kamilglazer.Vendi.dto.response.JwtResponse;
import com.kamilglazer.Vendi.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_ShouldReturnJwtResponse_WhenValidRequest() {
        RegisterRequest request = new RegisterRequest("test@test.com", "password", "John", "Doe", "123456789");
        JwtResponse mockResponse = new JwtResponse("mock-token","Registration successful", USER_ROLE.CUSTOMER);

        when(authenticationService.register(request)).thenReturn(mockResponse);
        ResponseEntity<JwtResponse> response = authController.register(request);

        assertEquals(200,response.getStatusCodeValue());
        assertEquals(mockResponse,response.getBody());
    }
}

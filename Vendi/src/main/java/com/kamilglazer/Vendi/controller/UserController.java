package com.kamilglazer.Vendi.controller;


import com.kamilglazer.Vendi.config.JwtService;
import com.kamilglazer.Vendi.dto.AddressDto;
import com.kamilglazer.Vendi.dto.response.UserResponse;
import com.kamilglazer.Vendi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getUserProfile(@RequestHeader("Authorization") String authHeader){
        String token = jwtService.getToken(authHeader);
        return ResponseEntity.ok(userService.findUserByJwtToken(token));
    }

    @PostMapping("/addAddress")
    public ResponseEntity<AddressDto> addAddress(@RequestHeader("Authorization") String authHeader, @RequestBody AddressDto address){
        String token = jwtService.getToken(authHeader);
        return ResponseEntity.ok(userService.addAddress(token, address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
        String token = jwtService.getToken(authHeader);
        userService.deleteAddress(token,id);
        return ResponseEntity.noContent().build();
    }
}

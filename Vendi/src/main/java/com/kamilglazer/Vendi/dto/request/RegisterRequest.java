package com.kamilglazer.Vendi.dto.request;


import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String fullName;
    private String mobile;
}

package com.kamilglazer.Vendi.service;

public interface EmailService {
    void sendVerificationEmail(String userEmail, String token);
}

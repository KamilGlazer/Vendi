package com.kamilglazer.Vendi.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("User not found");
    }
}

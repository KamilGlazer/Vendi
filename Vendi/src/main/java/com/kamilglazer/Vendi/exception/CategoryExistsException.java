package com.kamilglazer.Vendi.exception;

public class CategoryExistsException extends RuntimeException {
    public CategoryExistsException(String message) {
        super(message);
    }
}

package com.kamilglazer.Vendi.exception;

public class CouponExistsException extends RuntimeException{
    public CouponExistsException(String message){
        super(message);
    }
}

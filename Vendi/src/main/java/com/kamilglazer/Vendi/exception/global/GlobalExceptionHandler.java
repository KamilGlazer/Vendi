package com.kamilglazer.Vendi.exception.global;


import com.kamilglazer.Vendi.exception.*;
import com.kamilglazer.Vendi.exception.errorEntity.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> userExceptionHandler(UserNotFoundException ex, WebRequest request) {
        ErrorDetails details = ErrorDetails.builder()
                .error(ex.getMessage())
                .details(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserWithThisEmailAlreadyExists.class)
    public ResponseEntity<ErrorDetails> userWithThisEmailAlreadyExistsHandler(UserWithThisEmailAlreadyExists ex, WebRequest request){
        ErrorDetails details = ErrorDetails.builder()
                .error(ex.getMessage())
                .details(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CouponExistsException.class)
    public ResponseEntity<ErrorDetails> CouponExistsHandler(CouponExistsException ex, WebRequest request){
        ErrorDetails details = ErrorDetails.builder()
                .error(ex.getMessage())
                .details(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<ErrorDetails> CouponNotFoundHandler(CouponNotFoundException ex, WebRequest request){
        ErrorDetails details = ErrorDetails.builder()
                .error(ex.getMessage())
                .details(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MailSendError.class)
    public ResponseEntity<ErrorDetails> CouponNotFoundHandler(MailSendError ex, WebRequest request){
        ErrorDetails details = ErrorDetails.builder()
                .error(ex.getMessage())
                .details(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
    }



}

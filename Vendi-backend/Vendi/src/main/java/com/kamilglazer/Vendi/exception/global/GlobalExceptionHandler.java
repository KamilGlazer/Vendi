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

    @ExceptionHandler({UserNotFoundException.class, UserWithThisEmailAlreadyExists.class, CouponExistsException.class, CouponNotFoundException.class,
            MailSendError.class, CategoryExistsException.class, CategoryNotFoundException.class,
            ProductNotFoundException.class, AddressException.class, ReviewException.class, CartNotFoundException.class,
            CartItemException.class
    })
    public ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        return createErrorResponse(ex, request);
    }

    private ResponseEntity<ErrorDetails> createErrorResponse(Exception ex, WebRequest request) {
        com.kamilglazer.Vendi.exception.errorEntity.ErrorDetails details = com.kamilglazer.Vendi.exception.errorEntity.ErrorDetails.builder()
                .error(ex.getMessage())
                .details(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

}

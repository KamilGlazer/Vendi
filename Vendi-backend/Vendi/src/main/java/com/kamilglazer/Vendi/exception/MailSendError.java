package com.kamilglazer.Vendi.exception;

public class MailSendError extends RuntimeException {
    public MailSendError(String message) {
        super(message);
    }
}

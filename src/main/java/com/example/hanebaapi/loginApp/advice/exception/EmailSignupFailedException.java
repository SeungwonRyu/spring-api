package com.example.hanebaapi.loginApp.advice.exception;

public class EmailSignupFailedException extends RuntimeException {
    public EmailSignupFailedException() {
        super();
    }

    public EmailSignupFailedException(String msg) {
        super(msg);
    }

    public EmailSignupFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

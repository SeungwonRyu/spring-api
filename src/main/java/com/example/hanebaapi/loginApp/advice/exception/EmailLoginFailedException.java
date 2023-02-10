package com.example.hanebaapi.loginApp.advice.exception;

public class EmailLoginFailedException extends RuntimeException {
    public EmailLoginFailedException() {
        super();
    }

    public EmailLoginFailedException(String msg) {
        super(msg);
    }

    public EmailLoginFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

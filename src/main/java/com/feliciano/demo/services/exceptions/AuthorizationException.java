package com.feliciano.demo.services.exceptions;

public class AuthorizationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthorizationException(String string) {
        super(string);
    }

    public AuthorizationException(String string, Throwable cause) {
        super(string, cause);
    }
}

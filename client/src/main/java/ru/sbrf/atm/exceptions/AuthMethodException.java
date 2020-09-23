package ru.sbrf.atm.exceptions;

public class AuthMethodException extends RuntimeException {

    public AuthMethodException(String message) {
        super(message);
    }

    public AuthMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthMethodException(Throwable cause) {
        super(cause);
    }

    public AuthMethodException() {
    }
}

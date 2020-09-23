package ru.sbrf.atm.exceptions;

public class CardException extends RuntimeException {

    public CardException(String message) {
        super(message);
    }

    public CardException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardException(Throwable cause) {
        super(cause);
    }

    public CardException() {
    }
}

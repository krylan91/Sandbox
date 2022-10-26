package me.aakrylov.sandbox.exceptions;

import org.springframework.http.HttpStatus;

public class GoAwayException extends RuntimeException {

    public GoAwayException() {
        super();
    }

    public GoAwayException(String message) {
        super(message);
    }

    public GoAwayException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoAwayException(Throwable cause) {
        super(cause);
    }

    public GoAwayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public GoAwayException(HttpStatus httpStatus, String statusText) {
        super("HttpStatus: " + httpStatus + ", description: " + statusText);
    }
}

package com.ph.kata_product_trial.exception;

public class FunctionalException extends RuntimeException {

    public FunctionalException(String message) {
        super(message);
    }

    public FunctionalException(String message, Throwable cause) {
        super(message, cause);
    }
}

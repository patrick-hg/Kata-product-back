package com.ph.kata_product_trial.exception;

public class AuthenticationFailedException extends FunctionalException {

    public AuthenticationFailedException() {
        super("Authentication Failed! Credentials doesn't match");
    }
}

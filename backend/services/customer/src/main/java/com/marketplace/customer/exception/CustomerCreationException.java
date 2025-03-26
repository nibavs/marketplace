package com.marketplace.customer.exception;

public class CustomerCreationException extends RuntimeException {
    public CustomerCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}

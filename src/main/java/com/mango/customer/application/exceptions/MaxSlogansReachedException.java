package com.mango.customer.application.exceptions;

public class MaxSlogansReachedException extends RuntimeException {
    public MaxSlogansReachedException(String message) {
        super(message);
    }
}

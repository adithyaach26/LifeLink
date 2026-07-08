package com.lifelink.lifelink_backend.exception;

public class DonorNotFoundException extends RuntimeException {

    public DonorNotFoundException(String message) {
        super(message);
    }
}
package com.example.bankapisberstart.exception_handling;

public class IdempotencyException extends RuntimeException {

    public IdempotencyException(String message) {
        super(message);
    }
}

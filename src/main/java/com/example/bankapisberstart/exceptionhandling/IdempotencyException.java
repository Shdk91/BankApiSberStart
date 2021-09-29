package com.example.bankapisberstart.exceptionhandling;

public class IdempotencyException extends RuntimeException {

    public IdempotencyException(String message) {
        super("Слишком много однотипных запросов");
    }
}

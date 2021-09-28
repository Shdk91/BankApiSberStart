package com.example.bankapisberstart.exceptionhandling;

public class NoSuchClientException extends RuntimeException {

    public NoSuchClientException(String message) {
        super(message);
    }
}

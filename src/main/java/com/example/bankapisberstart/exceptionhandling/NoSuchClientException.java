package com.example.bankapisberstart.exceptionhandling;

public class NoSuchClientException extends RuntimeException {

    private static final String MESSAGE = "Клиент {} не найден";

    public NoSuchClientException(String message) {
        super(MESSAGE.replace("{}", message));
    }
}

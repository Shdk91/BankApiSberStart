package com.example.bankapisberstart.exceptionhandling;

public class IncorrectNumberException extends RuntimeException {

    private static final String MESSAGE = "Не верно указан номер  {} карты/счета";

    public IncorrectNumberException(String message) {
        super(MESSAGE.replace("{}", message));
    }
}

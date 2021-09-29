package com.example.bankapisberstart.exceptionhandling;

public class NoSuchCardException extends RuntimeException {

    private static final String MESSAGE = "Активная Карта(ы) для {} не найдена";


    public NoSuchCardException(String message) {
        super(MESSAGE.replace("{}", message));
    }
}

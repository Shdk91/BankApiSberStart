package com.example.bankapisberstart.exceptionhandling;

public class NoSuchAccountException extends RuntimeException {

    private static final String MESSAGE = "Активный счет(а) для {} не найдены";

    public NoSuchAccountException(String message) {
        super(MESSAGE.replace("{}", message));
    }
}

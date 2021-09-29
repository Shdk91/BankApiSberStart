package com.example.bankapisberstart.exceptionhandling;

public class NoSuchCounterpartyException extends RuntimeException {

    private static final String MESSAGE = "Контрагенты для {} не найдены";

    public NoSuchCounterpartyException(String message) {
        super(MESSAGE.replace("{}", message));
    }
}

package com.example.bankapisberstart.exceptionhandling;

public class DuplicateCounterpartyException extends RuntimeException {

    private static final String MESSAGE = "{} такой контрагент уже существует";

    public DuplicateCounterpartyException(String message) {
        super(MESSAGE.replace("{}", message));
    }
}

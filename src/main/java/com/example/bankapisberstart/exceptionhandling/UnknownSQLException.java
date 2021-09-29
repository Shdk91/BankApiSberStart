package com.example.bankapisberstart.exceptionhandling;

public class UnknownSQLException extends RuntimeException {
    private static final String MESSAGE = "{} Попробуйте позже";

    public UnknownSQLException(String message) {
        super(MESSAGE.replace("{}", message));
    }
}

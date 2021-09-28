package com.example.bankapisberstart.exceptionhandling;

public class UnknownSQLException extends RuntimeException {

    public UnknownSQLException(String message) {
        super(message);
    }
}

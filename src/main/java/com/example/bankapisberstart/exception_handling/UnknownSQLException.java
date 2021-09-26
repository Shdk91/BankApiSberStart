package com.example.bankapisberstart.exception_handling;

public class UnknownSQLException extends RuntimeException{

    public UnknownSQLException(String message) {
        super(message);
    }
}

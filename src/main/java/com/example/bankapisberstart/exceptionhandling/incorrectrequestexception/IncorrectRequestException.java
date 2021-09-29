package com.example.bankapisberstart.exceptionhandling.incorrectrequestexception;

public class IncorrectRequestException extends RuntimeException{
    public IncorrectRequestException(String message) {
        super(message);
    }
}

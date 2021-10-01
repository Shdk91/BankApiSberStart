package com.example.bankapisberstart.exceptionhandling.incorrectrequestexception;

public class IncorrectNumberException extends IncorrectRequestException {

    private static final String MESSAGE = "Не верно указан номер  {} карты/счета";

    public IncorrectNumberException(String message) {
        super(MESSAGE.replace("{}", message));
    }
}

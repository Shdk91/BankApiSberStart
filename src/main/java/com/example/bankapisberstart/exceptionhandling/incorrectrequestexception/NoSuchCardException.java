package com.example.bankapisberstart.exceptionhandling.incorrectrequestexception;

public class NoSuchCardException extends IncorrectRequestException {

    private static final String MESSAGE = "Активная Карта(ы) для {} не найдена";


    public NoSuchCardException(String message) {
        super(MESSAGE.replace("{}", message));
    }
}

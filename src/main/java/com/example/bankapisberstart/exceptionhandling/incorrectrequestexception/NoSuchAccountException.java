package com.example.bankapisberstart.exceptionhandling.incorrectrequestexception;

public class NoSuchAccountException extends IncorrectRequestException {

    private static final String MESSAGE = "Активный счет(а) для {} не найдены";

    public NoSuchAccountException(String message) {
        super(MESSAGE.replace("{}", message));
    }
}

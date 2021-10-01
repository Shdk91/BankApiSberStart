package com.example.bankapisberstart.exceptionhandling.incorrectrequestexception;

public class NoSuchClientException extends IncorrectRequestException {

    private static final String MESSAGE = "Клиент {} не найден";

    public NoSuchClientException(String message) {
        super(MESSAGE.replace("{}", message));
    }
}

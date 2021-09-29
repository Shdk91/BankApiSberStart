package com.example.bankapisberstart.exceptionhandling.incorrectrequestexception;

public class NoSuchCounterpartyException extends IncorrectRequestException {

    private static final String MESSAGE = "Контрагенты для {} не найдены";

    public NoSuchCounterpartyException(String message) {
        super(MESSAGE.replace("{}", message));
    }
}

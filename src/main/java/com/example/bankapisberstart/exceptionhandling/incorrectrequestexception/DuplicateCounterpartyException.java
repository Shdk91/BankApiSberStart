package com.example.bankapisberstart.exceptionhandling.incorrectrequestexception;

public class DuplicateCounterpartyException extends IncorrectRequestException {

    private static final String MESSAGE = "{} такой контрагент уже существует";

    public DuplicateCounterpartyException(String message) {
        super(MESSAGE.replace("{}", message));
    }
}

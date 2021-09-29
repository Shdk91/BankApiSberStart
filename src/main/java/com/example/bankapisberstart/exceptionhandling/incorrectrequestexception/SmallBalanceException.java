package com.example.bankapisberstart.exceptionhandling.incorrectrequestexception;

public class SmallBalanceException extends IncorrectRequestException {

    private static final String MESSAGE = "Не хватает денег на счете {}";
    public SmallBalanceException(String message) {
        super(MESSAGE.replace("{}", message));
    }
}

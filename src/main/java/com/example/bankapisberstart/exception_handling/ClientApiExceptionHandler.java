package com.example.bankapisberstart.exception_handling;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ClientIncorrectData> handleException(NoSuchClientException exception) {
        ClientIncorrectData clientIncorrectData = new ClientIncorrectData();
        clientIncorrectData.setInfo(exception.getMessage());

        return new ResponseEntity<>(clientIncorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ClientIncorrectData> handleException(UnknownSQLException exception) {
        ClientIncorrectData clientIncorrectData = new ClientIncorrectData();
        clientIncorrectData.setInfo(exception.getMessage());

        return new ResponseEntity<>(clientIncorrectData, HttpStatus.LOCKED);
    }

}

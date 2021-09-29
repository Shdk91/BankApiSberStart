package com.example.bankapisberstart.exceptionhandling;


import com.example.bankapisberstart.exceptionhandling.incorrectrequestexception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ClientIncorrectData> handleException(IncorrectRequestException exception) {
        ClientIncorrectData clientIncorrectData = new ClientIncorrectData();
        clientIncorrectData.setInfo(exception.getMessage());

        return new ResponseEntity<>(clientIncorrectData, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<ClientIncorrectData> handleException(UnknownSQLException exception) {
        ClientIncorrectData clientIncorrectData = new ClientIncorrectData();
        clientIncorrectData.setInfo(exception.getMessage());

        return new ResponseEntity<>(clientIncorrectData, HttpStatus.LOCKED);
    }

    @ExceptionHandler
    public ResponseEntity<ClientIncorrectData> handleException(IdempotencyException exception) {
        ClientIncorrectData clientIncorrectData = new ClientIncorrectData();
        clientIncorrectData.setInfo(exception.getMessage());

        return new ResponseEntity<>(clientIncorrectData, HttpStatus.LOCKED);
    }

}

package com.test.accountmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler
    public ResponseEntity handleGenericException (Exception exception) {
        return new ResponseEntity (exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

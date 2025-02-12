package com.postgrestoopensearch.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Void> internalServerException(Exception ex) {
        System.out.println(ex.getStackTrace());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

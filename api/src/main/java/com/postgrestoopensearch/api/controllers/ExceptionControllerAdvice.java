package com.postgrestoopensearch.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Void> internalServerException(Exception ex) {
        log.error("Internal server error ", ex);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

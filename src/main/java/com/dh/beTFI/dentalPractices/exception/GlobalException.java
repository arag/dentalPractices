package com.dh.beTFI.dentalPractices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestHandler(BadRequestException brhe){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR: "+
                brhe.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> notFoundHandler(ResourceNotFoundException rnfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: "+
                rnfe.getMessage());
    }
}

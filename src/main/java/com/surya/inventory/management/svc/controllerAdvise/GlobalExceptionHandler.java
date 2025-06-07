package com.surya.inventory.management.svc.controllerAdvise;

import com.surya.inventory.management.svc.exceptions.InvalidInputException;
import com.surya.inventory.management.svc.exceptions.RoleMismatchError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoleMismatchError.class)
    public ResponseEntity<String> roleMissMatchExceptionHandler(RoleMismatchError roleMismatchError){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(roleMismatchError.getMessage());
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> invalidInputExceptionHandler(InvalidInputException invalidInputException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(invalidInputException.getMessage());
    }
}

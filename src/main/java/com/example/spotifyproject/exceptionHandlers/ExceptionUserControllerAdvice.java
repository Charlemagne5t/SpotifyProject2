package com.example.spotifyproject.exceptionHandlers;

import com.example.spotifyproject.exceptionHandlers.errorDetailsClasses.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ExceptionUserControllerAdvice {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> SQLIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException exception) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setInformation("Unique constraint failed!");
        errorDetails.setStackTraceInfo(exception.getMessage());
        return ResponseEntity
                .badRequest()
                .body(errorDetails);
    }

}

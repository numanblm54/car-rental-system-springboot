package com.numan.Ornek3.Models;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(message));
    }

    @ExceptionHandler(MyException.class)
    public ResponseEntity<ErrorResponse> handleMyException(MyException ex) {

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }

}

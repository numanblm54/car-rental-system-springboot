package com.numan.Ornek3.exception;

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


    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(
            BaseException ex) {

        ErrorResponse response =
                new ErrorResponse(
                        ex.getErrorCode(),
                        ex.getMessage()
                );

        return ResponseEntity
                .status(ex.getErrorCode().getHttpStatus())
                .body(response);
    }
    
    
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
    	
    	ErrorResponse response = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
    	return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
    			.body(response);
    }
    
    
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponse> BusinessRuleException(BusinessRuleException ex){
    	
    	ErrorResponse response = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
    	return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
    			.body(response);
    }
    
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException ex){
    	
    	ErrorResponse response = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
    	return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
    			.body(response);
    }

           

}

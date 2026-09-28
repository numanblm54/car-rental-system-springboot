package com.numan.Ornek3.exception;


import java.time.LocalDateTime;
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

        if (ex.getMessages() != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse(ex.getMessages()));
        }

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }
    
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiErrorResponse> handleAppException(AppException ex){
    	ApiErrorResponse response = new ApiErrorResponse(ex.getErrorCode(),ex.getErrorCode().getMessage());
    	
    	return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
    			.body(response);
    	
    }
    
    @ExceptionHandler(AppExceptionB.class)
    public ResponseEntity<ApiErrorResponseB> handleAppExceptionB(AppExceptionB ex){
    	
    	ApiErrorResponseB response = new ApiErrorResponseB(ex.getErrorCode(), ex.getMessage(), LocalDateTime.now());
    	
    	return ResponseEntity.status(ex.getErrorCode().getStatus())
    			.body(response);
    	
    }
           

}

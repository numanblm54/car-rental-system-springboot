package com.numan.Ornek3.exception;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ApiErrorResponse {
	
	private ErrorCode errorCode;
    private String message;
    private LocalDateTime timestamp;
    
    public ApiErrorResponse(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
	
	
		
	    

	   
}

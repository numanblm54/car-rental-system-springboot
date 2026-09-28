package com.numan.Ornek3.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiErrorResponseB {
	
	private ErrorCodeB errorCode;
	private String message;
	private LocalDateTime timestamp;
	
    public ApiErrorResponseB(ErrorCodeB errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
	

}

package com.numan.Ornek3.exception;

import java.time.LocalDateTime;
import java.util.List;


import lombok.Getter;


@Getter
public class ErrorResponse {
	

    private ErrorCode errorCode;
    private String message;
    private LocalDateTime timestamp;
    private List<String> messages;

    public ErrorResponse(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(List<String> messages) {
        this.messages = messages;
        this.timestamp = LocalDateTime.now();
    }
    
    
}

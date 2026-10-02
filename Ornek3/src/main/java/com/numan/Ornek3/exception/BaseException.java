package com.numan.Ornek3.exception;

import java.security.PrivateKey;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {
	
	private final ErrorCode errorCode;
	
	protected BaseException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
	
    protected BaseException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    protected BaseException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
	
	

}

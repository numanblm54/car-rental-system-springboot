package com.numan.Ornek3.exception;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class AppExceptionB extends RuntimeException{
	private final ErrorCodeB errorCode;
	
	
	public AppExceptionB( ErrorCodeB errorCode, String message) {
		
		super(message);
		this.errorCode = errorCode;
				
		
	}

}

package com.numan.Ornek3.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
	
	private final ErrorCode errorCode;
	
	public AppException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	
	

}

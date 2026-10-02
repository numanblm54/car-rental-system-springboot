package com.numan.Ornek3.exception;

public class ResourceNotFoundException extends BaseException{
	
	public ResourceNotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}
	
	public ResourceNotFoundException(ErrorCode errorCode, String message) {
		super(errorCode, message);
	}

}

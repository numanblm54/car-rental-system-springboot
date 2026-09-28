package com.numan.Ornek3.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	
	RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found"),
	VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Validation error."),
	DUPLİCATE_RESOURCE(HttpStatus.CONFLICT, "Duplicate resource."),
	BUSSİNES_RULE_VIOLATION(HttpStatus.BAD_REQUEST, "Bussines rule violation.");
	
	
	private final HttpStatus httpStatus;
	private final String message;
	
	private ErrorCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
		
	}
	
	

}

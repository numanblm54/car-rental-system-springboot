package com.numan.Ornek3.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCodeB {
	
	RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND),
	VALIDATION_ERROR(HttpStatus.BAD_REQUEST),
	DUPLİCATE_RESOURCE(HttpStatus.CONFLICT),
	BUSSİNES_RULE_VIOLATION(HttpStatus.BAD_REQUEST);
	
	private final HttpStatus status;
	

	ErrorCodeB(HttpStatus status) {
	    this.status = status;
	    
	}
	
}

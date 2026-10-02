package com.numan.Ornek3.exception;

public class DuplicateResourceException extends BaseException {
	
    public DuplicateResourceException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DuplicateResourceException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}

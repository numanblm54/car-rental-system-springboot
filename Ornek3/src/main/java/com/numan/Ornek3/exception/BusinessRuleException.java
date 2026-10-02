package com.numan.Ornek3.exception;

public class BusinessRuleException extends BaseException{
	
    public BusinessRuleException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BusinessRuleException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}

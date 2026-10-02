package com.numan.Ornek3.exception;

public class ImportValidationException extends BaseException {

    public ImportValidationException(
            ErrorCode errorCode,
            String message) {

        super(errorCode, message);
    }

}

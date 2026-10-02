package com.numan.Ornek3.exception;

public class FileOperationException extends BaseException {
	
    public FileOperationException(
            ErrorCode errorCode,
            String message) {

        super(errorCode, message);
    }

    public FileOperationException(
            ErrorCode errorCode,
            String message,
            Throwable cause) {

        super(errorCode, message, cause);
    }

}

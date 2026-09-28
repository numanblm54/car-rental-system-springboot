package com.numan.Ornek3.exception;

import java.util.List;

public class MyException extends RuntimeException {
    private List<String> messages;

    public MyException(String message) {
        super(message);
    }

    public MyException(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
 }

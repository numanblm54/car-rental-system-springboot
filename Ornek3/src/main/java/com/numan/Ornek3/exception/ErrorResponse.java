package com.numan.Ornek3.exception;

import java.util.List;


import lombok.Getter;


@Getter
public class ErrorResponse {
	

    private List<String> messages;

    public ErrorResponse(List<String> messages) {
        this.messages = messages;
    }

    public ErrorResponse(String message) {
        this.messages = List.of(message);
    }
    
    
}

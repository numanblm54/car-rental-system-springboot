package com.numan.Ornek3.Models;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String message;
    
    public ErrorResponse(String message) {
        this.message = message;
    }

}

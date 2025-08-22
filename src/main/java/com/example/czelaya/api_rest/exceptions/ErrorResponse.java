package com.example.czelaya.api_rest.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final String message;
    private final int statusCode;
    private final LocalDateTime timestamp;
    private final String errorDetails;

    public ErrorResponse(String message, int statusCode, String errorDetails) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.errorDetails = errorDetails;
    }
}

package com.uur.Authentications.dtos;

import java.time.LocalDateTime;


public class ErrorDto {
    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorDto(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

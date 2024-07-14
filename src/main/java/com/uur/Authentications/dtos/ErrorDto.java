package com.uur.Authentications.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorDto {
    private LocalDateTime timestamp;
    private String message;
    private String details;
}

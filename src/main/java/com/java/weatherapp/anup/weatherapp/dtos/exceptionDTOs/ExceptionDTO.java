package com.java.weatherapp.anup.weatherapp.dtos.exceptionDTOs;

import org.springframework.http.HttpStatus;

public class ExceptionDTO {
    private String message;
    private HttpStatus status;
    public ExceptionDTO(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}

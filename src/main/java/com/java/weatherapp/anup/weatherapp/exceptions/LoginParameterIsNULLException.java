package com.java.weatherapp.anup.weatherapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.PARTIAL_CONTENT)
public class LoginParameterIsNULLException extends Exception {
    public LoginParameterIsNULLException(String message) {
        super(message);
    }
}

package com.java.weatherapp.anup.weatherapp.advices;

import com.java.weatherapp.anup.weatherapp.dtos.exceptionDTOs.ExceptionDTO;
import com.java.weatherapp.anup.weatherapp.exceptions.LoginParameterIsNULLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = AuthControllerAdvice.class)
public class AuthControllerAdvice {

    @ExceptionHandler(LoginParameterIsNULLException.class)
    public ResponseEntity<ExceptionDTO> handleLoginParameterIsNULLException(LoginParameterIsNULLException e) {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(e.getMessage(),HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleException(Exception e) {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(e.getMessage()+", stacktrace:"+e.getStackTrace(),HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.java.weatherapp.anup.weatherapp.advices;

import com.java.weatherapp.anup.weatherapp.controller.AnalyticsController;
import com.java.weatherapp.anup.weatherapp.dtos.exceptionDTOs.ExceptionDTO;
import com.java.weatherapp.anup.weatherapp.exceptions.InvalidExceptionToken;
import com.java.weatherapp.anup.weatherapp.exceptions.NNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = AnalyticsController.class)
public class AnalyticsControllerAdvice {

    @ExceptionHandler(NNotValidException.class)
    public ResponseEntity<ExceptionDTO> handleNNotValidException(NNotValidException e) {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(e.getMessage()+", stacktrace:"+e.getStackTrace(), HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidExceptionToken.class)
    public ResponseEntity<ExceptionDTO> handleInvalidTokenException(InvalidExceptionToken e) {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }
}

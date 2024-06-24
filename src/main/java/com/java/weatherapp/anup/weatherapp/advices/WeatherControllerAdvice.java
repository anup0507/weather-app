package com.java.weatherapp.anup.weatherapp.advices;

import com.java.weatherapp.anup.weatherapp.controller.WeatherController;
import com.java.weatherapp.anup.weatherapp.dtos.exceptionDTOs.ExceptionDTO;
import com.java.weatherapp.anup.weatherapp.exceptions.CityNameNotFoundException;
import com.java.weatherapp.anup.weatherapp.exceptions.InvalidExceptionToken;
import com.java.weatherapp.anup.weatherapp.exceptions.InvalidParameterException;
import com.java.weatherapp.anup.weatherapp.exceptions.UnableTogetDataFromThirdPartyAPI;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.text.ParseException;

@ControllerAdvice(assignableTypes = WeatherController.class)
public class WeatherControllerAdvice {
    @ExceptionHandler(InvalidExceptionToken.class)
    public ResponseEntity<ExceptionDTO> handleInvalidTokenException(InvalidExceptionToken e) {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ExceptionDTO> handleInvalidParameterException(InvalidParameterException e)
    {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CityNameNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleCityNameNotFoundException(CityNameNotFoundException e)
    {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UnableTogetDataFromThirdPartyAPI.class)
    public ResponseEntity<ExceptionDTO> handleUnabletogetDataFromThirdPartyAPIException(UnableTogetDataFromThirdPartyAPI e)
    {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleFileNotFoundException(UnableTogetDataFromThirdPartyAPI e)
    {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(JSONException.class)
    public ResponseEntity<ExceptionDTO> handleJSONException(JSONException e)
    {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ExceptionDTO> handleParseException(ParseException e)
    {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

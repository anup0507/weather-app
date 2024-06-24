package com.java.weatherapp.anup.weatherapp.controller;

import com.java.weatherapp.anup.weatherapp.dtos.weatherapiDTOs.WeatherApiDataRequestDTO;
import com.java.weatherapp.anup.weatherapp.dtos.weatherapiDTOs.WeatherResponseDTO;
import com.java.weatherapp.anup.weatherapp.exceptions.CityNameNotFoundException;
import com.java.weatherapp.anup.weatherapp.exceptions.InvalidExceptionToken;
import com.java.weatherapp.anup.weatherapp.exceptions.InvalidParameterException;
import com.java.weatherapp.anup.weatherapp.exceptions.UnableTogetDataFromThirdPartyAPI;
import com.java.weatherapp.anup.weatherapp.services.AuthService;
import com.java.weatherapp.anup.weatherapp.services.WeatherAPIThirdPartyService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {
    private WeatherAPIThirdPartyService weatherAPIThirdPartyService;
    private AuthService authService;

    public WeatherController(WeatherAPIThirdPartyService weatherService,AuthService authService) {
        this.weatherAPIThirdPartyService = weatherService;
        this.authService = authService;
    }
    @PostMapping("/getweatherForecast/{token}")
    public WeatherResponseDTO getweatherForecast(@PathVariable("token")String token, @RequestBody WeatherApiDataRequestDTO weatherApiDataRequestDTO) throws CityNameNotFoundException, InvalidParameterException,InvalidExceptionToken, UnableTogetDataFromThirdPartyAPI {
        if(!authService.ValidateToken(token))
        {
            throw new InvalidExceptionToken("Given Token is not valid. Either it is empty, or logged out. Please get a token first using /login api and then pass it here to access any weather api's.");
        }
        return weatherAPIThirdPartyService.getWeatherForecast(weatherApiDataRequestDTO,token);
    }

    @GetMapping("/getallCountries/{token}")
    public List<String> getallCountries(@PathVariable("token")String token) throws FileNotFoundException, JSONException, ParseException, InvalidExceptionToken {
        if(!authService.ValidateToken(token))
        {
            throw new InvalidExceptionToken("Given Token is not valid. Either it is empty, or logged out. Please get a token first using /login api and then pass it here to access any weather api's.");
        }
        return weatherAPIThirdPartyService.getallCountriesList();
    }

    @GetMapping("/getAllCities/{token}/{countryname}")
    public List<String> getAllCities(@PathVariable("countryname")String CountryName,@PathVariable("token") String token) throws FileNotFoundException, JSONException, ParseException, InvalidExceptionToken {
        if(!authService.ValidateToken(token))
        {
            throw new InvalidExceptionToken("Given Token is not valid. Either it is empty, or logged out. Please get a token first using /login api and then pass it here to access any weather api's.");
        }
        return weatherAPIThirdPartyService.getallCitiesList(CountryName);
    }

}

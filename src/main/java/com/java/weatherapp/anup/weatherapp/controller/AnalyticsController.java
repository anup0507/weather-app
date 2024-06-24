package com.java.weatherapp.anup.weatherapp.controller;

import com.java.weatherapp.anup.weatherapp.dtos.analyticsDTOs.TopNCitiesDTO;
import com.java.weatherapp.anup.weatherapp.dtos.analyticsDTOs.TopNCountriesDTO;
import com.java.weatherapp.anup.weatherapp.dtos.analyticsDTOs.TopNUsersDataDTO;
import com.java.weatherapp.anup.weatherapp.exceptions.InvalidExceptionToken;
import com.java.weatherapp.anup.weatherapp.exceptions.NNotValidException;
import com.java.weatherapp.anup.weatherapp.services.AnalyticsWeatherAPIThirdPartyService;

import com.java.weatherapp.anup.weatherapp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analytics/")
public class AnalyticsController {
    @Autowired
    private AnalyticsWeatherAPIThirdPartyService analyticsWeatherAPIThirdPartyService;
    @Autowired
    private AuthService authService;

    @GetMapping("/getTopNUsers/{token}/{n}")
    public TopNUsersDataDTO getTopNUsers(@PathVariable("n")int n,@PathVariable("token")String token) throws NNotValidException, InvalidExceptionToken {
        if(n==0)
        {
            throw new NNotValidException("N must be greater than 0");
        }
        if(!authService.ValidateToken(token))
        {
            throw new InvalidExceptionToken("Given Token is not valid. Either it is empty, or logged out. Please get a token first using /login api and then pass it here to access any weather api's.");
        }
        return analyticsWeatherAPIThirdPartyService.getTopNUsersUsingWeatherAPiForecastService(n);
    }
    @GetMapping("/gettopNCities/{token}/{n}")
    public TopNCitiesDTO getTopNCities(@PathVariable("n")int n,@PathVariable("token")String token) throws NNotValidException, InvalidExceptionToken {
        if(n==0)
        {
            throw new NNotValidException("N must be greater than 0");
        }
        if(!authService.ValidateToken(token))
        {
            throw new InvalidExceptionToken("Given Token is not valid. Either it is empty, or logged out. Please get a token first using /login api and then pass it here to access any weather api's.");
        }
        return analyticsWeatherAPIThirdPartyService.getTopnMostqueriedCitiesinWeatherAPiForecastService(n);
    }
    @GetMapping("/gettopNCountries/{token}/{n}")
    public TopNCountriesDTO getTopNCountries(@PathVariable("n")int n,@PathVariable("token")String token) throws NNotValidException, InvalidExceptionToken {
        if(n==0)
        {
            throw new NNotValidException("N must be greater than 0");
        }
        if(!authService.ValidateToken(token))
        {
            throw new InvalidExceptionToken("Given Token is not valid. Either it is empty, or logged out. Please get a token first using /login api and then pass it here to access any weather api's.");
        }
        return analyticsWeatherAPIThirdPartyService.getTopNMostqueriedCountriesinWeatherAPiForecastService(n);
    }

}

package com.java.weatherapp.anup.weatherapp.services;

import com.java.weatherapp.anup.weatherapp.dtos.analyticsDTOs.TopNCitiesDTO;
import com.java.weatherapp.anup.weatherapp.dtos.analyticsDTOs.TopNCountriesDTO;
import com.java.weatherapp.anup.weatherapp.dtos.analyticsDTOs.TopNUsersDataDTO;

import java.util.List;

public interface AnalyticsInterface {
    public TopNUsersDataDTO getTopNUsersUsingWeatherAPiForecastService(int n);
    TopNCitiesDTO getTopnMostqueriedCitiesinWeatherAPiForecastService(int n);
    TopNCountriesDTO getTopNMostqueriedCountriesinWeatherAPiForecastService(int n);
}

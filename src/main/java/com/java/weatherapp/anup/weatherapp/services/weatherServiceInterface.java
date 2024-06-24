package com.java.weatherapp.anup.weatherapp.services;

import com.java.weatherapp.anup.weatherapp.dtos.weatherapiDTOs.WeatherApiDataRequestDTO;
import com.java.weatherapp.anup.weatherapp.dtos.weatherapiDTOs.WeatherResponseDTO;
import com.java.weatherapp.anup.weatherapp.exceptions.CityNameNotFoundException;
import com.java.weatherapp.anup.weatherapp.exceptions.InvalidParameterException;
import com.java.weatherapp.anup.weatherapp.exceptions.UnableTogetDataFromThirdPartyAPI;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.FileNotFoundException;
import java.util.List;

public interface weatherServiceInterface {
    public WeatherResponseDTO getWeatherForecast(WeatherApiDataRequestDTO re,String token) throws CityNameNotFoundException, InvalidParameterException, UnableTogetDataFromThirdPartyAPI;
    public List<String> getallCountriesList() throws FileNotFoundException, JSONException, ParseException;
    public List<String> getallCitiesList(String CountryName) throws FileNotFoundException, JSONException, ParseException;
}

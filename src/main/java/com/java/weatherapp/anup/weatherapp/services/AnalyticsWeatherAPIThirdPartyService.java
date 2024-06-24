package com.java.weatherapp.anup.weatherapp.services;

import com.java.weatherapp.anup.weatherapp.dtos.analyticsDTOs.*;
import com.java.weatherapp.anup.weatherapp.models.weatherapimodels.apiResponseData.WeatherData;
import com.java.weatherapp.anup.weatherapp.repositories.authrepositories.SimpleTokenBasedAuthRepos.UserDataRepository;
import com.java.weatherapp.anup.weatherapp.repositories.thirdpartyrepo.analyticsrepostories.AnalyticsWeatherAPIRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalyticsWeatherAPIThirdPartyService implements  AnalyticsInterface {
    private final AnalyticsWeatherAPIRepository analyticsWeatherAPIRepository;

    public AnalyticsWeatherAPIThirdPartyService(AnalyticsWeatherAPIRepository analyticsWeatherAPIRepository) {
        this.analyticsWeatherAPIRepository = analyticsWeatherAPIRepository;
    }
    @Override
    public TopNUsersDataDTO getTopNUsersUsingWeatherAPiForecastService(int n) {
        List<Map.Entry<String, Integer>> result =this.analyticsWeatherAPIRepository.getTopNUsersUsingWeatherAPiForecastService(n);
        TopNUsersDataDTO topNUsersDataDTO=new TopNUsersDataDTO();
        List<TopNUserData> topnNusersDataDTOS=new ArrayList<>();
        for(Map.Entry<String, Integer> entry : result) {
            TopNUserData data=new TopNUserData();
            data.setCount(entry.getValue());
            data.setToken(entry.getKey());
            topnNusersDataDTOS.add(data);
        }
        topNUsersDataDTO.setTopnusers(topnNusersDataDTOS);
        return topNUsersDataDTO;
    }

    @Override
    public TopNCitiesDTO getTopnMostqueriedCitiesinWeatherAPiForecastService(int n) {
        List<Map.Entry<String, Integer>> result =this.analyticsWeatherAPIRepository.getTopnMostqueriedCitiesinWeatherAPiForecastService(n);
        TopNCitiesDTO topNCitiesDTOS=new TopNCitiesDTO();
        List<TopNCityData> topNCityData=new ArrayList<>();
        for(Map.Entry<String, Integer> entry : result) {
            TopNCityData data=new TopNCityData();
            data.setCount(entry.getValue());
            data.setCityName(entry.getKey());
            topNCityData.add(data);
        }
        topNCitiesDTOS.setTopncities(topNCityData);
        return topNCitiesDTOS;
    }

    @Override
    public TopNCountriesDTO getTopNMostqueriedCountriesinWeatherAPiForecastService(int n) {
        List<Map.Entry<String, Integer>> result =this.analyticsWeatherAPIRepository.getTopNMostqueriedCountriesinWeatherAPiForecastService(n);
        TopNCountriesDTO topNCountriesDTO=new TopNCountriesDTO();
        List<TopNCountryData> topNCountryData=new ArrayList<>();
        for(Map.Entry<String, Integer> entry : result) {
            TopNCountryData data=new TopNCountryData();
            data.setCount(entry.getValue());
            data.setCountryName(entry.getKey());
            topNCountryData.add(data);
        }
        topNCountriesDTO.setTopNcountries(topNCountryData);
        return topNCountriesDTO;
    }
    public void updateAnalyticsAfterGettingAPIData(WeatherData weatherData,String token)
    {
        this.analyticsWeatherAPIRepository.updateAnalyticsAfterGettingAPIData(weatherData,token);
    }
}

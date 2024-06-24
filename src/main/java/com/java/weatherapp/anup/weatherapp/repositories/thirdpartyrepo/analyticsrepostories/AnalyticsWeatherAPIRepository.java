package com.java.weatherapp.anup.weatherapp.repositories.thirdpartyrepo.analyticsrepostories;

import com.java.weatherapp.anup.weatherapp.dtos.analyticsDTOs.TopNCitiesDTO;
import com.java.weatherapp.anup.weatherapp.dtos.analyticsDTOs.TopNCountriesDTO;
import com.java.weatherapp.anup.weatherapp.dtos.analyticsDTOs.TopNUserData;
import com.java.weatherapp.anup.weatherapp.dtos.analyticsDTOs.TopNUsersDataDTO;
import com.java.weatherapp.anup.weatherapp.models.weatherapimodels.apiResponseData.WeatherData;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalyticsWeatherAPIRepository {
    private HashMap<String,Integer> citiesCount=new HashMap<>();
    private HashMap<String,Integer> countriesCount=new HashMap<>();
    private HashMap<String,Integer> usersCount=new HashMap<>();
    private List<Map.Entry<String, Integer>> gettopNthings(int n, HashMap<String,Integer> mp)
    {
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<String, Integer> entry : mp.entrySet()) {
            pq.offer(entry);
            if (pq.size() > n) {
                pq.poll();
            }
        }
        List<Map.Entry<String, Integer>> result = new ArrayList<>();

        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> entry = pq.poll();
            result.add(entry);
        }
        return result;
    }
    public List<Map.Entry<String, Integer>> getTopNUsersUsingWeatherAPiForecastService(int n) {
        List<Map.Entry<String, Integer>> result =gettopNthings(n,usersCount);
        return result;
    }
    public List<Map.Entry<String, Integer>> getTopnMostqueriedCitiesinWeatherAPiForecastService(int n) {
        List<Map.Entry<String, Integer>> result =gettopNthings(n,citiesCount);
        return result;
    }
    public List<Map.Entry<String, Integer>> getTopNMostqueriedCountriesinWeatherAPiForecastService(int n) {
        List<Map.Entry<String, Integer>> result =gettopNthings(n,countriesCount);
        return result;
    }
    public void updateAnalyticsAfterGettingAPIData(WeatherData weatherData, String token)
    {
        if(usersCount.containsKey(token))
        {
            usersCount.put(token,usersCount.get(token)+1);
        }
        else
        {
            usersCount.put(token,1);
        }
        if(citiesCount.containsKey(weatherData.getLocation().getName()))
        {
            citiesCount.put(weatherData.getLocation().getName(),citiesCount.get(weatherData.getLocation().getName())+1);
        }
        else
        {
            citiesCount.put(weatherData.getLocation().getName(),1);
        }
        if(countriesCount.containsKey(weatherData.getLocation().getCountry()))
        {
            countriesCount.put(weatherData.getLocation().getCountry(),countriesCount.get(weatherData.getLocation().getCountry())+1);
        }
        else
        {
            countriesCount.put(weatherData.getLocation().getCountry(),1);
        }
    }
}

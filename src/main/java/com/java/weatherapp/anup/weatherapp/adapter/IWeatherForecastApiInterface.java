package com.java.weatherapp.anup.weatherapp.adapter;

import com.java.weatherapp.anup.weatherapp.models.weatherapimodels.apiResponseData.Filter;

import java.util.List;

public interface IWeatherForecastApiInterface {
    public String getWeatherForecast(Filter filter);

}

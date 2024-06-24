package com.java.weatherapp.anup.weatherapp.models.weatherapimodels.apiResponseData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherData {
    private Location location;
    private Forecast forecast;
}

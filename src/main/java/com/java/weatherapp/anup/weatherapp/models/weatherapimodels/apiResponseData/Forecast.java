package com.java.weatherapp.anup.weatherapp.models.weatherapimodels.apiResponseData;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Forecast {

    private List<ForecastDay> forecastdays;

}

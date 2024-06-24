package com.java.weatherapp.anup.weatherapp.dtos.weatherapiDTOs;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Forecast_resp {
    private List<ForeCastDay_resp> forecastdays;
}

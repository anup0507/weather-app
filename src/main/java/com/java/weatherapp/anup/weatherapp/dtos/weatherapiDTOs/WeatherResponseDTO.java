package com.java.weatherapp.anup.weatherapp.dtos.weatherapiDTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponseDTO {
    private Location_resp location_resp;
    private Forecast_resp forecast_resp;
}

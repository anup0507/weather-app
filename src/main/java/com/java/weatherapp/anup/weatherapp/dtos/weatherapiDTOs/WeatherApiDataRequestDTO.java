package com.java.weatherapp.anup.weatherapp.dtos.weatherapiDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WeatherApiDataRequestDTO {
    private String cityname;
    private int futuredates;
}

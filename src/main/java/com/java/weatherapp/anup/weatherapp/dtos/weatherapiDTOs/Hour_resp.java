package com.java.weatherapp.anup.weatherapp.dtos.weatherapiDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hour_resp {
    private float temperature_in_celsius;
    private float temp_in_farenheight;
    private float feelslike_celsius;
    private float feelslike_farenheight;
    private int chance_of_rain;
    private int chance_of_snow;
    private float heatindex_celsius;
    private float heatindex_farenheight;
}

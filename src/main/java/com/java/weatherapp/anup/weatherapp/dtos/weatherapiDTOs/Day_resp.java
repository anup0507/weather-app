package com.java.weatherapp.anup.weatherapp.dtos.weatherapiDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Day_resp {
    private float maxtemp_celsius;
    private float mintemp_farenheight;
    private float maxtemp_farenheight;
    private float mintemp_celsius;
    private float avgtemp_celsius;
    private float avgtemp_farenheight;
    private float averageHumidity;
    private int daily_chance_of_rain;
    private int daily_chance_of_snow;
    private int daily_will_it_snow;
    private int daily_will_it_rain;
}

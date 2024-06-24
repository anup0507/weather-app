package com.java.weatherapp.anup.weatherapp.models.weatherapimodels.apiResponseData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Day {
    private float maxtemp_c;
    private float mintemp_f;
    private float maxtemp_f;
    private float mintemp_c;
    private float avgtemp_c;
    private float avgtemp_f;
    private float maxwind_mph;
    private float maxwind_kph;
    private float minwind_mph;
    private float minwind_kph;
    private float avghumidity;
    private float totalsnow_cm;
    private float totalprecip_mm;
    private float totalprecip_in;
    private int daily_chance_of_rain;
    private int daily_chance_of_snow;
    private int daily_will_it_snow;
    private int daily_will_it_rain;
}

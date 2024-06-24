package com.java.weatherapp.anup.weatherapp.models.weatherapimodels.apiResponseData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hour {
    private float temp_c;
    private float temp_f;
    private int is_day;
    private float wind_mph;
    private float wind_kph;
    private int wind_degree;
    private float feelslike_c;
    private float feelslike_f;
    private int chance_of_rain;
    private int chance_of_snow;
    private float windchill_c;
    private float windchill_f;
    private float heatindex_c;
    private float heatindex_f;

}

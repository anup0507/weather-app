package com.java.weatherapp.anup.weatherapp.models.weatherapimodels.apiResponseData;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Filter {
    private String CityorCountryName;
    private int numberoffutureDays;
}

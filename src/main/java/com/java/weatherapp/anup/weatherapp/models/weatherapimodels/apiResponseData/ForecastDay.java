package com.java.weatherapp.anup.weatherapp.models.weatherapimodels.apiResponseData;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ForecastDay {
    private String date;
    private Astro astro;
    private Day day;
    //@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<Hour> hours;
}

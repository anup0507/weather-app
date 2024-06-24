package com.java.weatherapp.anup.weatherapp.models.weatherapimodels.apiResponseData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Astro {
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private String moonphase;
    private int moon_illumination;
    private boolean is_moon_up;
    private  boolean is_sun_up;
}

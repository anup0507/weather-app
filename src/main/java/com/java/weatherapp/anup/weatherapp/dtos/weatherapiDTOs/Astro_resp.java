package com.java.weatherapp.anup.weatherapp.dtos.weatherapiDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Astro_resp {
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private String moonphase;
    private int moon_illumination;
    private boolean is_moon_up;
    private  boolean is_sun_up;
}

package com.java.weatherapp.anup.weatherapp.dtos.weatherapiDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location_resp {
    private String name;
    private String region;
    private String country;
    private String lat;
    private String lon;
    private String localtime;

}

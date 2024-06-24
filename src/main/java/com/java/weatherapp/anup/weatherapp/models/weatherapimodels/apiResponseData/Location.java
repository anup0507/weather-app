package com.java.weatherapp.anup.weatherapp.models.weatherapimodels.apiResponseData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private String name;
    private String region;
    private String country;
    private String lat;
    private String lon;
    private String tz_id;
    private String localtime;

}

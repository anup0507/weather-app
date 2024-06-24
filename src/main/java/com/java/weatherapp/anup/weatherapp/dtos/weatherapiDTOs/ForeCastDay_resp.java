package com.java.weatherapp.anup.weatherapp.dtos.weatherapiDTOs;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ForeCastDay_resp {
    private String date;
    private Astro_resp astro;
    private Day_resp day;
    //@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<Hour_resp> hours;
}

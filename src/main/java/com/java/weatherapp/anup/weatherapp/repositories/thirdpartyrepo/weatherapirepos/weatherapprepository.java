package com.java.weatherapp.anup.weatherapp.repositories.thirdpartyrepo.weatherapirepos;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
@Repository
public class weatherapprepository {
    private HashMap<String,Integer> userCountusingWeatherService;
    private HashMap<String,Integer> citiesCountusedWeatherService;
}

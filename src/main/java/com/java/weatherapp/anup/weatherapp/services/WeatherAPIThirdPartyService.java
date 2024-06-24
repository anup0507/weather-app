package com.java.weatherapp.anup.weatherapp.services;

import com.java.weatherapp.anup.weatherapp.adapter.WeatherApiThirdPartyImpl;
import com.java.weatherapp.anup.weatherapp.dtos.weatherapiDTOs.*;
import com.java.weatherapp.anup.weatherapp.exceptions.CityNameNotFoundException;
import com.java.weatherapp.anup.weatherapp.exceptions.UnableTogetDataFromThirdPartyAPI;
import com.java.weatherapp.anup.weatherapp.models.weatherapimodels.apiResponseData.*;
import com.java.weatherapp.anup.weatherapp.repositories.thirdpartyrepo.cities_countries_repositories.City_country_repository;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import org.springframework.stereotype.Service;


import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherAPIThirdPartyService implements  weatherServiceInterface{
    @Value("${city_country_json_path}")
    private String city_country_json_path;
    private final WeatherApiThirdPartyImpl weatherAPiThirdPartyImpl;
    private final AnalyticsWeatherAPIThirdPartyService analyticsWeatherAPIThirdPartyService;
    private final City_country_repository city_country_repository;
    public WeatherAPIThirdPartyService(WeatherApiThirdPartyImpl weatherApiThirdPartyImpl,
                                       AnalyticsWeatherAPIThirdPartyService analyticsWeatherAPIThirdPartyService,
                                       City_country_repository city_country_repository
                                       ) {

        this.weatherAPiThirdPartyImpl = weatherApiThirdPartyImpl;
        this.analyticsWeatherAPIThirdPartyService=analyticsWeatherAPIThirdPartyService;
        this.city_country_repository=city_country_repository;
    }

    @Override
    public WeatherResponseDTO getWeatherForecast(WeatherApiDataRequestDTO requestDTO,String token) throws CityNameNotFoundException, InvalidParameterException, UnableTogetDataFromThirdPartyAPI {
        if(!ValidateRequestDTOParams(requestDTO))
        {
            throw new InvalidParameterException("Please check the params, city, country names should not be blank and should not contain special characters and start date and end date should be valid. Start Date should not be after endDate.");
        }
        Filter filter=createFilterObjectFromDTO(requestDTO);

        WeatherData weatherData;
        String data="";
        try {
             data=this.weatherAPiThirdPartyImpl.getWeatherForecast(filter);
        }
        catch (Exception ex)
        {
            throw new UnableTogetDataFromThirdPartyAPI("Sorry! unable to fetch data from source at this time. Please try again later.");
        }
        WeatherResponseDTO weatherResponseDTO;
        JSONObject body= null;
        try {
            body=new JSONObject(data);

        } catch (Exception e) {
            weatherResponseDTO=null;
            return weatherResponseDTO;
        }
        try {
            weatherData =getweatherDataObjectFromJsonObject(body);
        } catch (JSONException e) {
            throw new CityNameNotFoundException("Given City Name data does not exist. Please try a different city name.");

        }
        this.analyticsWeatherAPIThirdPartyService.updateAnalyticsAfterGettingAPIData(weatherData,token);
        weatherResponseDTO=getResponseDTOFromAPIResponseObject(weatherData);
        return weatherResponseDTO;

}

    @Override
    public List<String> getallCountriesList() throws FileNotFoundException, JSONException, ParseException {
        List<String> result=new ArrayList<>();
        List<String> allcountriesList=this.city_country_repository.getAllCountriesList(city_country_json_path);
        return allcountriesList;
    }

    @Override
    public List<String> getallCitiesList(String CountryName) throws FileNotFoundException, JSONException, ParseException {
        List<String> result=new ArrayList<>();
        List<String> allcitiesList=this.city_country_repository.getALlCitiesListFromCountry(city_country_json_path,CountryName);
        return allcitiesList;
    }


    private boolean ValidateRequestDTOParams(WeatherApiDataRequestDTO requestDTO)
{
    if(requestDTO==null)return false;
    if(requestDTO.getCityname()==null)return false;
    if(requestDTO.getFuturedates()==0)
        requestDTO.setFuturedates(1);
    return true;
}

    private Filter createFilterObjectFromDTO(WeatherApiDataRequestDTO requestDTO) {
        Filter filter=new Filter();
        filter.setCityorCountryName(requestDTO.getCityname());
        filter.setNumberoffutureDays(requestDTO.getFuturedates());
        return filter;
    }

    private String FormWebAPiURl()
    {
        String finalurl="";
        return finalurl;
    }

private WeatherResponseDTO getResponseDTOFromAPIResponseObject(WeatherData weatherData) {
    WeatherResponseDTO weatherResponseDTO=new WeatherResponseDTO();
    Location_resp locationResp=new Location_resp();
    locationResp.setCountry(weatherData.getLocation().getCountry());
    locationResp.setName(weatherData.getLocation().getName());
    locationResp.setRegion(weatherData.getLocation().getRegion());
    locationResp.setLon(weatherData.getLocation().getLon());
    locationResp.setLat(weatherData.getLocation().getLat());
    locationResp.setLocaltime(weatherData.getLocation().getLocaltime());
    Forecast_resp forecast_resp=new Forecast_resp();
    List<ForeCastDay_resp> foreCastDayResps=getForcastDaysResponseDTO(weatherData.getForecast());
    forecast_resp.setForecastdays(foreCastDayResps);
    weatherResponseDTO.setForecast_resp(forecast_resp);
    weatherResponseDTO.setLocation_resp(locationResp);
    return weatherResponseDTO;
}

private List<ForeCastDay_resp> getForcastDaysResponseDTO(Forecast forecast) {
    List<ForeCastDay_resp> foreCastDayResps=new ArrayList<>();
    for(int i=0;i<forecast.getForecastdays().toArray().length;i++)
    {
        ForeCastDay_resp foreCastDayResp=new ForeCastDay_resp();
        ForecastDay forecastDay=forecast.getForecastdays().get(i);
        Day_resp dayResp=new Day_resp();
        dayResp.setAverageHumidity(forecastDay.getDay().getAvghumidity());
        dayResp.setDaily_will_it_rain(forecastDay.getDay().getDaily_will_it_rain());
        dayResp.setMaxtemp_celsius(forecastDay.getDay().getMaxtemp_c());
        dayResp.setMintemp_celsius(forecastDay.getDay().getMintemp_c());
        dayResp.setDaily_will_it_snow(forecastDay.getDay().getDaily_will_it_snow());
        dayResp.setDaily_chance_of_rain(forecastDay.getDay().getDaily_chance_of_rain());
        dayResp.setMaxtemp_farenheight(forecastDay.getDay().getMaxtemp_f());
        dayResp.setMintemp_farenheight(forecastDay.getDay().getMintemp_f());
        dayResp.setDaily_chance_of_snow(forecastDay.getDay().getDaily_chance_of_snow());
        dayResp.setAvgtemp_celsius(forecastDay.getDay().getAvgtemp_c());
        dayResp.setAvgtemp_farenheight(forecastDay.getDay().getAvgtemp_f());
        Astro_resp astroResp=new Astro_resp();
        astroResp.setMoonset(forecastDay.getAstro().getMoonset());
        astroResp.setMoonrise(forecastDay.getAstro().getMoonrise());
        astroResp.setSunrise(forecastDay.getAstro().getSunrise());
        astroResp.setSunset(forecastDay.getAstro().getSunset());
        astroResp.set_moon_up(forecastDay.getAstro().is_moon_up());
        astroResp.set_sun_up(forecastDay.getAstro().is_sun_up());
        astroResp.setMoonphase(forecastDay.getAstro().getMoonphase());
        astroResp.setMoon_illumination(forecastDay.getAstro().getMoon_illumination());
        List<Hour_resp> hourResps=getHoursResponseDTOFromAPIObject(forecastDay.getHours());
        foreCastDayResp.setAstro(astroResp);
        foreCastDayResp.setDay(dayResp);
        foreCastDayResp.setHours(hourResps);
        foreCastDayResp.setDate(forecastDay.getDate());
        foreCastDayResps.add(foreCastDayResp);
    }
    return foreCastDayResps;
}

private List<Hour_resp> getHoursResponseDTOFromAPIObject(List<Hour> hours) {
    List<Hour_resp> hourResps=new ArrayList<>();
    for(int i=0;i<hours.size();i++)
    {
        Hour_resp hourResp=new Hour_resp();
        Hour hour=hours.get(i);
        hourResp.setChance_of_rain(hour.getChance_of_rain());
        hourResp.setTemperature_in_celsius(hour.getTemp_c());
        hourResp.setTemp_in_farenheight(hour.getTemp_f());
        hourResp.setChance_of_snow(hour.getChance_of_snow());
        hourResp.setFeelslike_celsius(hour.getFeelslike_c());
        hourResp.setFeelslike_farenheight(hour.getFeelslike_f());
        hourResp.setHeatindex_celsius(hour.getHeatindex_c());
        hourResp.setHeatindex_farenheight(hour.getHeatindex_f());
        hourResps.add(hourResp);
    }
    return hourResps;
}

private WeatherData getweatherDataObjectFromJsonObject(JSONObject body) throws JSONException {
    WeatherData weatherData=new WeatherData();
    JSONObject locationObject=body.getJSONObject("location");
    Location location=getlocationDataFromJson(locationObject);
    JSONObject forecastobject=body.getJSONObject("forecast");
    Forecast forecasts=getForecastDataFromJsonObject(forecastobject);
    weatherData.setForecast(forecasts);
    weatherData.setLocation(location);
    return weatherData;
}

private Forecast getForecastDataFromJsonObject(JSONObject forecastobject) {

    JSONArray forecastdaysarray;
    try {
        forecastdaysarray=forecastobject.getJSONArray("forecastday");
    } catch (JSONException e) {
        return null;
    }
    // Forecast forecasts=getforcastDaysObject(forecastdaysarray);

    Forecast forecasts=new Forecast();
    forecasts.setForecastdays(getforcastDaysObject(forecastdaysarray));
    return forecasts;
}

private List<ForecastDay> getforcastDaysObject(JSONArray forecastdaysjsonarray) {
    //Forecast forecast=new Forecast();
    List<ForecastDay> forecastDays=new ArrayList<>();
    for(int i=0; i<forecastdaysjsonarray.length(); i++) {
        ForecastDay forecastday=new ForecastDay();
        try {
            JSONObject jobj=forecastdaysjsonarray.getJSONObject(i);
            forecastday.setDay(getDayObjectFromJsonObject(jobj.getJSONObject("day")));
            forecastday.setAstro(getAstroObjectFromJsonObject(jobj.getJSONObject("astro")));
            forecastday.setHours(gethoursfromjsonarray(jobj.getJSONArray("hour")));
            forecastday.setDate(jobj.getString("date"));
            forecastDays.add(forecastday);
        } catch (JSONException e) {
            return null;
        }
    }
    return forecastDays;
}

private List<Hour> gethoursfromjsonarray(JSONArray jarray) {
    List<Hour> hours=new ArrayList<>();
    for(int i=0;i<jarray.length();i++)
    {
        Hour h=new Hour();
        try {
            JSONObject jobj=jarray.getJSONObject(i);
            h.setFeelslike_f(Float.parseFloat(jobj.getString("feelslike_f")));
            h.setFeelslike_c(Float.parseFloat(jobj.getString("feelslike_c")));
            h.setChance_of_rain(Integer.parseInt(jobj.getString("chance_of_rain")));
            h.setChance_of_snow(Integer.parseInt(jobj.getString("chance_of_snow")));
            h.setTemp_f(Float.parseFloat(jobj.getString("temp_f")));
            h.setWind_kph(Float.parseFloat(jobj.getString("wind_kph")));
            h.setWind_mph(Float.parseFloat(jobj.getString("wind_mph")));
            h.setTemp_c(Float.parseFloat(jobj.getString("temp_c")));
            h.setTemp_f(Float.parseFloat(jobj.getString("temp_f")));
            h.setIs_day(Integer.parseInt(jobj.getString("is_day")));
            hours.add(h);
        }
        catch (Exception e) {
            System.out.println("exception:"+e.toString());
        }
    }
    return hours;
}

private Astro getAstroObjectFromJsonObject(JSONObject astro) {
    Astro astros=new Astro();
    try {
        astros.set_moon_up(Boolean.parseBoolean(astro.getString("is_moon_up")));
        astros.setMoonset(astro.getString("moonset"));
        astros.setMoonrise(astro.getString("moonrise"));
        astros.setMoonphase(astro.getString("moon_phase"));
        astros.setMoon_illumination(Integer.parseInt(astro.getString("moon_illumination")));
        astros.set_sun_up(Boolean.parseBoolean(astro.getString("is_sun_up")));
        astros.setSunrise(astro.getString("sunrise"));
        astros.setSunset(astro.getString("sunset"));
        astros.set_moon_up(Boolean.parseBoolean(astro.getString("is_moon_up")));
    }
    catch (JSONException e)
    {
        System.out.println("exception: "+e.toString());
    }
    return astros;
}

private Day getDayObjectFromJsonObject(JSONObject jobj) {
    Day day= new Day();
    try {
        day.setAvgtemp_c(Float.parseFloat(jobj.getString("avgtemp_c")));
        day.setAvghumidity(Float.parseFloat(jobj.getString("avghumidity")));
        day.setAvgtemp_c(Float.parseFloat(jobj.getString("avgtemp_c")));
        day.setMaxtemp_c(Float.parseFloat(jobj.getString("maxtemp_c")));
        day.setMintemp_c(Float.parseFloat(jobj.getString("mintemp_c")));
        day.setMaxtemp_f(Float.parseFloat(jobj.getString("maxtemp_f")));
        day.setMintemp_f(Float.parseFloat(jobj.getString("mintemp_f")));
        day.setDaily_chance_of_rain(Integer.parseInt(jobj.getString("daily_chance_of_rain")));
        day.setDaily_will_it_snow(Integer.parseInt(jobj.getString("daily_will_it_snow")));
        day.setMaxwind_kph(Float.parseFloat(jobj.getString("maxwind_kph")));
        day.setMinwind_kph(Float.parseFloat(jobj.getString("minwind_kph")));
        day.setMaxwind_mph(Float.parseFloat(jobj.getString("maxwind_mph")));
        day.setMinwind_mph(Integer.parseInt(jobj.getString("minwind_mph")));
        day.setDaily_will_it_rain(Integer.parseInt(jobj.getString("daily_will_it_rain")));
        day.setDaily_will_it_snow(Integer.parseInt(jobj.getString("daily_will_it_snow")));
        day.setTotalprecip_in(Integer.parseInt(jobj.getString("totalprecip_in")));
        day.setTotalsnow_cm(Float.parseFloat(jobj.getString("totalsnow_cm")));
        day.setTotalprecip_mm(Float.parseFloat(jobj.getString("totalprecip_mm")));

    } catch (JSONException e) {

    }
    return day;
}

private Location getlocationDataFromJson(JSONObject locationObject) {
    Location location=new Location();
    try {
        location.setName(locationObject.getString("name"));
        location.setRegion(locationObject.getString("region"));
        location.setCountry(locationObject.getString("country"));
        location.setLon(locationObject.getString("lon"));
        location.setLat(locationObject.getString("lat"));
        location.setTz_id(locationObject.getString("tz_id"));
        location.setLocaltime(locationObject.getString("localtime"));
    } catch (JSONException e) {
        return null;
    }
    return location;
}
}

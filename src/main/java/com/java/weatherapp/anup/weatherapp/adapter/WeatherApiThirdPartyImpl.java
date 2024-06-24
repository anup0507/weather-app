package com.java.weatherapp.anup.weatherapp.adapter;

import com.java.weatherapp.anup.weatherapp.models.weatherapimodels.apiResponseData.Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherApiThirdPartyImpl implements IWeatherForecastApiInterface{

    @Value("${webapi.apikey}")
    //private String apikey="b1b6c4f3f386459892383739242006";
    private String apikey;
    @Value("${thirdparty.webapi.baseurl}")
    //private String url="http://api.weatherapi.com/v1/forecast.json?key=b1b6c4f3f386459892383739242006&q=London&days=1&aqi=no&alerts=no";
    private String baseurl;
    @Value("${thirdparty.webapi.lasppart}")
    private String lastpart;
    @Value("${thirdParty.CityParamName}")
    private String cityparamname;
    @Value("${thirdParty.daysinfuture}")
    private String daysinfuture;
    private RestTemplateBuilder restTemplateBuilder;

    public WeatherApiThirdPartyImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public String getWeatherForecast(Filter filter) {
        String finalURl=FormUrlUsingParameters(filter);
        RestTemplate restTemplate=restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(finalURl,String.class,entity);
        if(responseEntity.getStatusCode()== HttpStatus.OK) {
            return responseEntity.getBody();
        }
        return null;
    }
    private String FormUrlUsingParameters(Filter filter)
    {
        String url=baseurl+apikey+cityparamname+filter.getCityorCountryName()+daysinfuture+filter.getNumberoffutureDays()+lastpart;
        return url;
    }
}

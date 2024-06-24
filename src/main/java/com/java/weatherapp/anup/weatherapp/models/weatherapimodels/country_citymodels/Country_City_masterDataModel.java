package com.java.weatherapp.anup.weatherapp.models.weatherapimodels.country_citymodels;

import com.java.weatherapp.anup.weatherapp.models.weatherapimodels.authModels.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Country_City_masterDataModel {
    private String country;
    private String subcountry;
    private String geonameid;
    private String name;
    @Override
    public int hashCode()
    {
        return Objects.hash(subcountry);
    }

    @Override
    public boolean equals(Object obj) {
        Country_City_masterDataModel other = (Country_City_masterDataModel) obj;
        int hash1=Objects.hash(other.getSubcountry());
        int hash2=Objects.hash(subcountry);
        return hash1==hash2;
    }

}

package com.java.weatherapp.anup.weatherapp.models.weatherapimodels.authModels;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class User
{
    private int userId;
    private String email;
    private String password;
    private String FullName;
    private String userName;
    private UserType usertype;
    @Override
    public int hashCode()
    {
        return Objects.hash(email,userName);
    }

    @Override
    public boolean equals(Object obj) {
        int hash1;
        int hash2;
        User u=(User)obj;
        hash1=Objects.hash(email,userName);
        hash2=Objects.hash(u.email,u.userName);
        return hash1==hash2;
    }

}

package com.java.weatherapp.anup.weatherapp.models.weatherapimodels.authModels;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserRole {
    private User user;
    private List<Role> roles;
   
    public UserRole(){
        roles = new ArrayList<>();
    }
    @Override
    public int hashCode()
    {
        return user.getUserId();
    }

    @Override
    public boolean equals(Object obj) {
        UserRole userRole = (UserRole) obj;
        return this.user.getUserId()==userRole.user.getUserId();
    }
}

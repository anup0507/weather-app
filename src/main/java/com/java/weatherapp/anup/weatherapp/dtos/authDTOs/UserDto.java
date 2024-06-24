package com.java.weatherapp.anup.weatherapp.dtos.authDTOs;


import com.java.weatherapp.anup.weatherapp.models.weatherapimodels.authModels.Role;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String email;
    private String token;
    private List<Role> roles;
}

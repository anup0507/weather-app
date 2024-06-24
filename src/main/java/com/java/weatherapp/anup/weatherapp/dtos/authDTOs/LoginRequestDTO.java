package com.java.weatherapp.anup.weatherapp.dtos.authDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String email;
    private String password;
    private String username;
    private String fullname;
}

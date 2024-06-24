package com.java.weatherapp.anup.weatherapp.dtos.analyticsDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TopNUsersDataDTO {
    private List<TopNUserData> topnusers;

}

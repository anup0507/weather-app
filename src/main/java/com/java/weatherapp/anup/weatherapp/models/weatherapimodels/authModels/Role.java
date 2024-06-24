package com.java.weatherapp.anup.weatherapp.models.weatherapimodels.authModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role {
    private String rolename;
    private RoleType roleType;
    private String description;
    public Role(){}
    public Role(String rolename, RoleType roleType, String description) {
        this.rolename = rolename;
        this.roleType = roleType;
        this.description = description;
    }
}

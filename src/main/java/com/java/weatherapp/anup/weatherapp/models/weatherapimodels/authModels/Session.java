package com.java.weatherapp.anup.weatherapp.models.weatherapimodels.authModels;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class Session {
    private User user;
    private String token;
    private Date expiringAt;
    private String sessionId;
    private SessionStatus_1 sessionStatus;
    @Override
    public int hashCode()
    {
        return Objects.hash(sessionId,expiringAt);
    }
    @Override
    public boolean equals(Object o) {

        Session s=(Session)o;
        int hash1=Objects.hash(sessionId,expiringAt);
        int hash2=Objects.hash(s.getSessionId(),expiringAt);
        return  hash1==hash2;
    }


}

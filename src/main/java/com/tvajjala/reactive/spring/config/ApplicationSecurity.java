package com.tvajjala.reactive.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "application.security")
public class ApplicationSecurity {


    private List<ApplicationUser> users=new ArrayList<>();


    public List<ApplicationUser> getUsers() {
        return users;
    }

    public void setUsers(List<ApplicationUser> users) {
        this.users = users;
    }
}

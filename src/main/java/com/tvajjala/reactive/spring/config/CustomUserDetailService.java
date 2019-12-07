package com.tvajjala.reactive.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CustomUserDetailService implements UserDetailsService {


    private Map<String, ApplicationUser> appUsers=new HashMap<>();

    public CustomUserDetailService(List<ApplicationUser> users){
        users.stream().collect(Collectors.toMap(user -> user.getUsername(), user->user));
    }

    @Override
    public ApplicationUser loadUserByUsername(String username) throws UsernameNotFoundException {

        if(!appUsers.containsKey(username))
            throw new UsernameNotFoundException("User "+ username+" Not found");

        return appUsers.get(username);
    }
}

package com.tvajjala.reactive.spring.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {


    public CustomAuthenticationEntryPoint(String realmName){
        super.setRealmName(realmName);
    }

    @Override
    public void setRealmName(String realmName) {
        super.setRealmName(realmName);
    }


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
       //customize your response here
        response.addHeader("WWW-Authenticate", "Basic realm=\"TVAJJALA\"");
        response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }
}

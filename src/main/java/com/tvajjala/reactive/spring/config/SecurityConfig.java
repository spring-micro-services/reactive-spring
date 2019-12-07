package com.tvajjala.reactive.spring.config;

import com.tvajjala.reactive.spring.filter.CustomAuthFilter;
import com.tvajjala.reactive.spring.filter.RequestContextFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableConfigurationProperties(ApplicationSecurity.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    ApplicationSecurity applicationSecurity;

    @Bean
    public FilterRegistrationBean<RequestContextFilter> contextHolderFilterBean(){

        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new RequestContextFilter());

        filterRegistrationBean.addUrlPatterns(new String[]{"/reactive/*"});

        return filterRegistrationBean;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable()
               .authorizeRequests()
               .antMatchers("/reactive/**")
               .authenticated()
               .and()
               .httpBasic()
               .and()
               .addFilterAt(new CustomAuthFilter(authenticationManager(),authenticationEntryPoint()), BasicAuthenticationFilter.class)
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Bean
    AuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint();
    }



    @Bean
    PasswordEncoder passwordEncoder(){
        return CustomPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService()).passwordEncoder(passwordEncoder());
    }


    @Bean
    UserDetailsService customUserDetailService(){
        return new CustomUserDetailService(applicationSecurity.getUsers());
    }

}

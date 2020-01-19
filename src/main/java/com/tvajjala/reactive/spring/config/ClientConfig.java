package com.tvajjala.reactive.spring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ThirupathiReddy Vajjala
 */
@Configuration
public class ClientConfig {

    @Bean
    ObjectMapper objectMapper(){

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new MoneyModule());
        return objectMapper;

    }


    @Bean
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){

        return restTemplateBuilder.build();
    }
}

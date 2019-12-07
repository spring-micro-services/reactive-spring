package com.tvajjala.reactive.spring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class ClientConfig {

    @Bean
    ObjectMapper objectMapper(){

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new MoneyModule());
        return objectMapper;

    }
}

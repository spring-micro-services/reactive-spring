package com.tvajjala.reactive.spring.config;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author ThirupathiReddy Vajjala
 */
public class MoneyModule extends SimpleModule {


    public MoneyModule(){
        this.addSerializer(BigDecimalSerializer.INSTANCE);
    }
}

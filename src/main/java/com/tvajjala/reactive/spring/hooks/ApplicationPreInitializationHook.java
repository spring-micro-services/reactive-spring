package com.tvajjala.reactive.spring.hooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationPreInitializationHook implements ApplicationListener<ApplicationStartingEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationPreInitializationHook.class);


    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {

        LOGGER.info("Application starting... ");//this will not print  as logger not instantiated by this time.
    }
}

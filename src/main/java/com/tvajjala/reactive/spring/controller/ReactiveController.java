package com.tvajjala.reactive.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReactiveController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReactiveController.class);


    @GetMapping(value = "/")
    public static String printTraceId() {

        LOGGER.info("printing tracking for this log");

        return "Hi...";
    }
}

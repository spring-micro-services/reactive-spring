package com.tvajjala.reactive.spring;

import brave.Tracer;
import com.tvajjala.reactive.spring.context.ThreadContextHolder;
import com.tvajjala.reactive.spring.hooks.ApplicationPostInitializationHook;
import com.tvajjala.reactive.spring.hooks.ApplicationPreInitializationHook;
import com.tvajjala.reactive.spring.hooks.ContextHandoverAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rx.plugins.RxJavaHooks;


/**
 * @author ThirupathiReddy Vajjala
 */
@SpringBootApplication
public class ReactiveSpringApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReactiveSpringApplication.class);

    @Autowired
    Tracer tracer;

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(ReactiveSpringApplication.class);
        springApplication.addListeners(new ApplicationPreInitializationHook(), new ApplicationPostInitializationHook());
        springApplication.run(args);

    }

    @Override
    public void run(String... args) {

        LOGGER.info("Application initialization completed ");

        /* Any scheduler actions (Scheduler.io) this code executes */
        RxJavaHooks.setOnScheduleAction(action0-> new ContextHandoverAction(action0, ThreadContextHolder.getThreadContext(), tracer));
    }
}

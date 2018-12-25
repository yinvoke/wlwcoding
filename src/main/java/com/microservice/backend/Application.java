package com.microservice.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class Application  {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    public static void main(String args[]){
        System.out.println( "Hello World JPA !" );
        SpringApplication.run(Application.class,args);
    }
}

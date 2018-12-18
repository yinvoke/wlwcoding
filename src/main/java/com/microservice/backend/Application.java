package com.microservice.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String args[]){
        System.out.println( "Hello World JPA !" );
        SpringApplication.run(Application.class,args);
    }
}

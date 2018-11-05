package com.microservice.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {
    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String home(){
        return "Hi,We are coding group,we are best!";
    }
}

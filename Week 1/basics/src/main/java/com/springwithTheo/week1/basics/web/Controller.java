package com.springwithTheo.week1.basics.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("greetingController")
public class Controller {

    @GetMapping("/")
    public String greeting(){
        return "Welcome Spring With Theo. It's Week 1 we're starting strong!!!";
    }
}

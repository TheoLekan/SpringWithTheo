package com.springwiththeo.week2.bootstrapping_spring_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/")
    public String index() {
        return "Hello, Spring!";
    }
}

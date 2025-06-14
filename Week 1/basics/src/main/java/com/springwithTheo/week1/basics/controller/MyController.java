package com.springwithTheo.week1.basics.controller;

import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    public String hello() {
        return "Hello from MyController!";
    }
}

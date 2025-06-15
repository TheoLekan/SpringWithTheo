package com.springwithTheo.week1.basics.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public String hello() {
        return "Hello from MyService!";
    }
}

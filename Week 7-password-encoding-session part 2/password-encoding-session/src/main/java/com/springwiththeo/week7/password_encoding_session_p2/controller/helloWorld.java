package com.springwiththeo.week7.password_encoding_session_p2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class helloWorld {

    @GetMapping
    public String hello() {
        return "Hello World!";
    }
}

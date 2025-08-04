package com.springwiththeo.week8.db_jwt_authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {


    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello Admin!";
    }

    @GetMapping("/user/hello")
    public String userHello() {
        return "Hello User!";
    }


}

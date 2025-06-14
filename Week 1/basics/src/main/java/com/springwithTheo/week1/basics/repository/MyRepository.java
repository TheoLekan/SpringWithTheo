package com.springwithTheo.week1.basics.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MyRepository {

    public String hello() {
        return "Hello from MyRepository!";
    }
}

package com.springwithTheo.week1.basics.service;

import org.springframework.stereotype.Component;

@Component
public class MeatPizza implements Pizza {
    @Override
    public String getPizza() {
        return "Meat Pizza is ready!";
    }
}

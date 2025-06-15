package com.springwithTheo.week1.basics.lazy;

import org.springframework.stereotype.Component;

@Component
public class EagerLoader {
    public EagerLoader() {
        System.out.println("EagerLoader is initialized!");
    }
}

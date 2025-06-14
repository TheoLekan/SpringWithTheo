package com.springwithTheo.week1.basics.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LazyLoader {
    public LazyLoader() {
        System.out.println("LazyLoader is initialized!");
    }
}

package com.springwithTheo.week1.basics.config;

import com.springwithTheo.week1.basics.service.Pizza;
import com.springwithTheo.week1.basics.service.VegPizza;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Pizza vegPizza() {
        return new VegPizza();
    }

}

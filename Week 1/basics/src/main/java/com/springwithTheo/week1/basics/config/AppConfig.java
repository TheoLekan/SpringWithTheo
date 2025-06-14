package com.springwithTheo.week1.basics.config;

import com.springwithTheo.week1.basics.service.MeatPizza;
import com.springwithTheo.week1.basics.service.Pizza;
import com.springwithTheo.week1.basics.service.VegPizza;
import com.springwithTheo.week1.basics.web.PizzaController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Primary
    @Bean(name = "vegPizzaThroughBeanName")
    public Pizza vegPizza() {
        return new VegPizza();
    }

    @Bean
    public  Pizza meatPizza() {
        return new MeatPizza();
    }

    @Bean(name = "pizzaControllerWithMeatPizza")
    public PizzaController pizzaController(@Qualifier("meatPizza") Pizza pizza) {
        return new PizzaController(pizza);
    }
    @Bean(name = "pizzaControllerWithVegPizza")
    public PizzaController pizzaController2(Pizza pizza) {
        return new PizzaController(pizza);
    }
}

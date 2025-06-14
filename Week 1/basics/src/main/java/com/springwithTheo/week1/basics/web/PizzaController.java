package com.springwithTheo.week1.basics.web;

import com.springwithTheo.week1.basics.service.VegPizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PizzaController {

    VegPizza vegPizza;

    @Autowired
    public PizzaController(VegPizza vegPizza) {
        this.vegPizza = vegPizza;
    }


    public String getPizza() {
        return vegPizza.getPizza();
    }


}

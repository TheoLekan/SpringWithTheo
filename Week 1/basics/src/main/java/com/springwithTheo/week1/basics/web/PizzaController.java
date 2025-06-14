package com.springwithTheo.week1.basics.web;

import com.springwithTheo.week1.basics.service.VegPizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PizzaController {

    @Autowired
    VegPizza vegPizza;

    /* * Constructor-based dependency injection
     * This is the preferred way of injecting dependencies in Spring.
     * It allows for better testability and immutability.
     */
   /* @Autowired
    public PizzaController(VegPizza vegPizza) {
        this.vegPizza = vegPizza;
    }*/


    /* * Setter-based dependency injection
     * This is another way of injecting dependencies in Spring.
     * It allows for more flexibility, but is less preferred than constructor-based injection.
     */
//    @Autowired
//    public void setVegPizza(VegPizza vegPizza) {
//        this.vegPizza = vegPizza;
//    }


    public String getPizza() {
        return vegPizza.getPizza();
    }


}

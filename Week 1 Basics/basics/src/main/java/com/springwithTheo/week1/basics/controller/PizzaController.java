package com.springwithTheo.week1.basics.controller;

import com.springwithTheo.week1.basics.service.Pizza;

//@Component
public class PizzaController {


    Pizza pizza;

    /* * Constructor-based dependency injection
     * This is the preferred way of injecting dependencies in Spring.
     * It allows for better testability and immutability.
     */
    // @Autowired
    public PizzaController(Pizza vegPizza) {
        this.pizza = vegPizza;
    }


    /* * Setter-based dependency injection
     * This is another way of injecting dependencies in Spring.
     * It allows for more flexibility, but is less preferred than constructor-based injection.
     */
//    @Autowired
//    public void setVegPizza(VegPizza vegPizza) {
//        this.vegPizza = vegPizza;
//    }

    public String getPizza() {
        return pizza.getPizza();
    }


    public void init() {
        System.out.println("PizzaController is initialized with " + pizza.getPizza());
    }

    public void destroy() {
        System.out.println("PizzaController is destroyed");
    }

}

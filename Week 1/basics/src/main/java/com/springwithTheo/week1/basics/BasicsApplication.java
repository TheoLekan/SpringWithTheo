package com.springwithTheo.week1.basics;

import com.springwithTheo.week1.basics.service.VegPizza;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BasicsApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(BasicsApplication.class, args);
		//PizzaController bean = ctx.getBean( PizzaController.class);
		// System.out.println(bean.getPizza());

		VegPizza vegPizza = ctx.getBean(VegPizza.class);
		System.out.println(vegPizza.getPizza());
	}

}

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

		// Get the VegPizza bean from the application context with method name "vegPizza".
		//DefaultBeanName is the method name in the AppConfig class.
		VegPizza vegPizza = ctx.getBean("vegPizza", VegPizza.class);
		System.out.println(vegPizza.getPizza());
	}

}

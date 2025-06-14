package com.springwithTheo.week1.basics;

import com.springwithTheo.week1.basics.controller.MyController;
import com.springwithTheo.week1.basics.controller.PizzaController;
import com.springwithTheo.week1.basics.lazy.LazyLoader;
import com.springwithTheo.week1.basics.repository.MyRepository;
import com.springwithTheo.week1.basics.service.MyService;
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
		//VegPizza vegPizza = ctx.getBean("vegPizzaThroughBeanName", VegPizza.class);
		//System.out.println(vegPizza.getPizza());

		//Getting Controller with VegPizza
		 PizzaController controllerWithVegPizza = ctx.getBean("pizzaControllerWithVegPizza",PizzaController.class);
		 System.out.println(controllerWithVegPizza.getPizza());

		//Getting Controller with MeatPizza
		PizzaController controllerWithMeatPizza = ctx.getBean("pizzaControllerWithMeatPizza",PizzaController.class);
		System.out.println(controllerWithMeatPizza.getPizza());

		MyController controller = ctx.getBean(MyController.class);
		System.out.println(controller.hello());

		MyService service = ctx.getBean(MyService.class);
		System.out.println(service.hello());

		MyRepository repository = ctx.getBean(MyRepository.class);
		System.out.println(repository.hello());

		LazyLoader lazyLoader = ctx.getBean(LazyLoader.class);
		System.out.println("LazyLoader bean has been retrieved from the context.");
	}

}

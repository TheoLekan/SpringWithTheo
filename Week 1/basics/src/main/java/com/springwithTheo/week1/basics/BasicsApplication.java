package com.springwithTheo.week1.basics;

import com.springwithTheo.week1.basics.web.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BasicsApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(BasicsApplication.class, args);
		Controller bean = ctx.getBean(Controller.class);
		System.out.println(bean.greeting());
	}

}

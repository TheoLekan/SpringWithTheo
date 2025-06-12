package com.springwithTheo.week1.basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class BasicsApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(BasicsApplication.class, args);
		System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));
	}

}

package com.springwithTheo.week1.basics;

import com.springwithTheo.week1.basics.scopes.PrototypeBean;
import com.springwithTheo.week1.basics.scopes.SingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BasicsApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(BasicsApplication.class, args);

		SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
		SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);
		SingletonBean singletonBean3 = ctx.getBean(SingletonBean.class);

		System.out.println("SingletonBean instances are the same: " + (singletonBean1 == singletonBean2 && singletonBean2 == singletonBean3));

		PrototypeBean prototypeBean1 = ctx.getBean(PrototypeBean.class);
		PrototypeBean prototypeBean2 = ctx.getBean(PrototypeBean.class);
		PrototypeBean prototypeBean3 = ctx.getBean(PrototypeBean.class);

		System.out.println("PrototypeBean instances are same: " + (prototypeBean1 == prototypeBean2 && prototypeBean2 == prototypeBean3));
	}


}

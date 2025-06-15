package com.springwithTheo.week1.basics.scopes;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON) // This annotation defines the scope of the bean as singleton
public class SingletonBean {

    public SingletonBean() {
        System.out.println("SingletonBean");
    }
}

package com.example.springcore.controller;

import com.example.springcore.service.LifecycleBean;
import com.example.springcore.service.ScopeBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ScopeAndLifecycleDemo {

    private final ApplicationContext context;

    public ScopeAndLifecycleDemo(ApplicationContext context) {
        this.context = context;
    }

    public void run() {
        System.out.println("---- So sánh Scope ----");
        ScopeBean s1 = context.getBean(ScopeBean.class);
        ScopeBean s2 = context.getBean(ScopeBean.class);
        System.out.println("Bean 1: " + s1);
        System.out.println("Bean 2: " + s2);

        System.out.println("---- Lifecycle ----");
        LifecycleBean lb = context.getBean(LifecycleBean.class);
    }
}

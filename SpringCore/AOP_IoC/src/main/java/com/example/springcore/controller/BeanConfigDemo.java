package com.example.springcore.controller;

import com.example.springcore.service.CustomServiceA;
import com.example.springcore.service.CustomServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanConfigDemo {
    private final CustomServiceA serviceA;
    private final CustomServiceB serviceB;

    @Autowired
    public BeanConfigDemo(CustomServiceA serviceA, CustomServiceB serviceB) {
        this.serviceA = serviceA;
        this.serviceB = serviceB;
    }

    public void run() {
        System.out.println(">>> @Bean demo:");
        serviceA.doSomething();
        serviceB.doSomething();
    }
}
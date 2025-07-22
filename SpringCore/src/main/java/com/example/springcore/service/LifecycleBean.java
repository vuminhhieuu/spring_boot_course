package com.example.springcore.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class LifecycleBean {
    public LifecycleBean() {
        System.out.println("LifecycleBean constructor called");
    }

    @PostConstruct
    public void init() {
        System.out.println("LifecycleBean init method called");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("LifecycleBean destroy method called");
    }
}
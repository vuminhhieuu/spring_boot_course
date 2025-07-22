package com.example.springcore.service;

import org.springframework.stereotype.Component;

@Component
public class HelloService {
    public String sayHello() {
        return "Xin chao tu Hello Service";
    }
}
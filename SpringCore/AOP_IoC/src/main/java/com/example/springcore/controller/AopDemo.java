package com.example.springcore.controller;

import com.example.springcore.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AopDemo {

    @Autowired
    private PaymentService paymentService;

    public void run() {
        System.out.println(">>> Bat dau AOP demo:");
        paymentService.pay();
    }
}
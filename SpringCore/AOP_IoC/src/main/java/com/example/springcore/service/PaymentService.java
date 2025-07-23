package com.example.springcore.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentService {
    public void pay() {
        System.out.println("PaymentService is processing payment!");
    }
}
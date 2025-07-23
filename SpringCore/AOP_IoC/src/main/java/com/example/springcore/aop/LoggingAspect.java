package com.example.springcore.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.springcore.sevice.PaymentService.pay(..))")
    public void logBeforePayment() {
        System.out.println("Bat dau goi phuong thuc pay()");
    }
}
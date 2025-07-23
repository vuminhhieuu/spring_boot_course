package com.example.springcore.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") // singleton, request, session
public class ScopeBean {
    public ScopeBean() {
        System.out.println("ScopeBean constructor called");
    }
}
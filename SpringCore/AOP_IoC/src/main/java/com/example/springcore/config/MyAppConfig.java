package com.example.springcore.config;

import com.example.springcore.service.CustomServiceA;
import com.example.springcore.service.CustomServiceB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAppConfig {

    @Bean
    public CustomServiceA customServiceA() {
        return new CustomServiceA();
    }

    @Bean
    public CustomServiceB customServiceB() {
        return new CustomServiceB();
    }
}

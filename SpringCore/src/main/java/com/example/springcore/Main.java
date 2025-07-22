package com.example.springcore;

import com.example.springcore.config.MyAppConfig;
import com.example.springcore.controller.BeanConfigDemo;
import com.example.springcore.controller.HelloController;
import com.example.springcore.controller.ScopeAndLifecycleDemo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.springcore")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class, MyAppConfig.class);

        System.out.println(">> Hello Demo:");
        context.getBean(HelloController.class).printHello();

        System.out.println(">> Scope & Lifecycle Demo:");
        context.getBean(ScopeAndLifecycleDemo.class).run();

        System.out.println(">> @Bean Config Demo:");
        context.getBean(BeanConfigDemo.class).run();

        context.close();
    }
}

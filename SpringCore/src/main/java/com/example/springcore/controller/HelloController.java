package com.example.springcore.controller;

import com.example.springcore.service.HelloService;
import com.example.springcore.service.TimeService;
import com.example.springcore.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloController {

    private final HelloService helloService;
    private final TimeService timeService;
    private final WeatherService weatherService;

    @Autowired
    public HelloController(HelloService helloService, TimeService timeService, WeatherService weatherService) {
        this.helloService = helloService;
        this.timeService = timeService;
        this.weatherService = weatherService;
    }

    public void printHello() {
        System.out.println(helloService.sayHello());
        System.out.println("Current time: " + timeService.getCurrentTime());
        System.out.println("Weather: " + weatherService.getWeather());
    }

}
package com.example.springcore.service;

import org.springframework.stereotype.Component;

@Component
public class WeatherService {
    public String getWeather() {
        return "Hôm nay trời nắng đẹp, nhiệt độ khoảng 30 độ C.";
    }
}
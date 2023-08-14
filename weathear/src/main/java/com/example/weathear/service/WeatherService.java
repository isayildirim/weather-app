package com.example.weathear.service;

import com.example.weathear.dto.WeatherDto;

public interface WeatherService {

    WeatherDto getWeatherByCityName(String cityName);
}

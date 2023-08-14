package com.example.weathear.controller;


import com.example.weathear.controller.validation.CityNameConstraint;
import com.example.weathear.dto.WeatherDto;
import com.example.weathear.service.WeatherService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/v1/api/weather" )
@Validated
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {

        this.weatherService = weatherService;
    }

    @GetMapping( "/{city}" )
    @RateLimiter( name = "basic" )
    public ResponseEntity<WeatherDto> getWeather(@PathVariable( "city" ) @NotNull @CityNameConstraint String city) {

        return ResponseEntity.ok(weatherService.getWeatherByCityName(city));
    }

}

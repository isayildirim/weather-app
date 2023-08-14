package com.example.weathear.service.impl;

import com.example.weathear.constants.Constants;
import com.example.weathear.dto.WeatherDto;
import com.example.weathear.dto.WeatherResponse;
import com.example.weathear.model.WeatherEntity;
import com.example.weathear.repository.WeahterRepository;
import com.example.weathear.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@CacheConfig( cacheNames = { "weathers" } )
public class WeatherServiceImpl implements WeatherService {

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final static Integer RENEWAL_MINUTES = 30;

    private final WeahterRepository weahterRepository;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();


    public WeatherServiceImpl(WeahterRepository weahterRepository, RestTemplate restTemplate) {

        this.weahterRepository = weahterRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable( key = "#cityName" )
    public WeatherDto getWeatherByCityName(String cityName) {

        Optional<WeatherEntity> weatherEntityOptional =
                weahterRepository.findFirstByRequestedCityNameOrderByUploadTimeDesc(cityName);

        return weatherEntityOptional.map(weather -> {
            if ( weather.getUploadTime().isBefore(LocalDateTime.now().minusMinutes(RENEWAL_MINUTES)) ) {
                return WeatherDto.convert(getWeatherFromWeatherStack(cityName));
            }
            return WeatherDto.convert(weather);
        }).orElseGet(() -> WeatherDto.convert(getWeatherFromWeatherStack(cityName)));
    }

    @CacheEvict( allEntries = true )
    @PostConstruct
    @Scheduled( fixedRateString = "10000" )
    public void clearCache() {

    }

    private WeatherEntity getWeatherFromWeatherStack(String city) {

        ResponseEntity<String> response = restTemplate.getForEntity(getWeatherStackUrl(city), String.class);

        try {
            WeatherResponse weatherResponse = objectMapper.readValue(response.getBody(), WeatherResponse.class);
            return saveWeatherEntity(city, weatherResponse);
        } catch ( JsonProcessingException e ) {
            throw new RuntimeException();
        }
    }

    private String getWeatherStackUrl(String city) {

        return Constants.API_URL + Constants.ACCESS_KEY_PARAM + Constants.API_KEY + Constants.QUERY_KEY_PARAM + city;
    }

    private WeatherEntity saveWeatherEntity(String city, WeatherResponse response) {

        WeatherEntity entity = new WeatherEntity(
                city,
                response.location().name(),
                response.location().country(),
                response.current().temperature(),
                LocalDateTime.now(),
                LocalDateTime.parse(response.location().localTime(), formatter));

        return weahterRepository.save(entity);
    }

}

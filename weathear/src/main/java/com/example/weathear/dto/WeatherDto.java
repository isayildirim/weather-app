package com.example.weathear.dto;

import com.example.weathear.model.WeatherEntity;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record WeatherDto(
        String cityName,
        String country,
        Integer temperature,
        @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm" )
        LocalDateTime updatedTime
) {

    public static WeatherDto convert(WeatherEntity weatherEntity) {

        return new WeatherDto(
                weatherEntity.getCityName(),
                weatherEntity.getCountry(),
                weatherEntity.getTemperature(),
                weatherEntity.getUploadTime()
        );
    }

}

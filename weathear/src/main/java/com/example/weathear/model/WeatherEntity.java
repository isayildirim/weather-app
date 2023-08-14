package com.example.weathear.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
public class WeatherEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String requestedCityName;

    private String cityName;

    private String country;

    private Integer temperature;

    private LocalDateTime uploadTime;

    private LocalDateTime responseLocalTime;

    public WeatherEntity(String id, String requestedCityName, String cityName, String country, Integer temperature,
                         LocalDateTime uploadTime, LocalDateTime responseLocalTime) {

        this.id = id;
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.uploadTime = uploadTime;
        this.responseLocalTime = responseLocalTime;
    }

    public WeatherEntity(String requestedCityName, String cityName, String country, Integer temperature,
                         LocalDateTime uploadTime, LocalDateTime responseLocalTime) {

        this.id = "";
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.uploadTime = uploadTime;
        this.responseLocalTime = responseLocalTime;
    }

    public WeatherEntity() {

    }

    public String getId() {

        return id;
    }

    public String getRequestedCityName() {

        return requestedCityName;
    }

    public String getCityName() {

        return cityName;
    }

    public String getCountry() {

        return country;
    }

    public Integer getTemperature() {

        return temperature;
    }

    public LocalDateTime getUploadTime() {

        return uploadTime;
    }

    public LocalDateTime getResponseLocalTime() {

        return responseLocalTime;
    }

}

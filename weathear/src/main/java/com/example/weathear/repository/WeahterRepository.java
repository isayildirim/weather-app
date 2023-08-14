package com.example.weathear.repository;

import com.example.weathear.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeahterRepository extends JpaRepository<WeatherEntity, String> {

    Optional<WeatherEntity> findFirstByRequestedCityNameOrderByUploadTimeDesc(String cityName);

}

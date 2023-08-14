package com.example.weathear.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.weathear.constants.Constants.WEATHER_CACHE_NAME;

@EnableCaching
@Configuration
public class CachingConfig {


    @Bean
    public CacheManager cacheManager() {

        return new ConcurrentMapCacheManager(WEATHER_CACHE_NAME);
    }

}

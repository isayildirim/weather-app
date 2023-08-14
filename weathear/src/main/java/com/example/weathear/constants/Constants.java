package com.example.weathear.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static String API_URL;

    public static String API_KEY;

    public static String ACCESS_KEY_PARAM = "?access_key=";

    public static String QUERY_KEY_PARAM = "&query=";

    public static String WEATHER_CACHE_NAME;

    @Value( "${weather-stack.api-url}" )
    public void setApiUrl(String apiUrl) {

        Constants.API_URL = apiUrl;
    }

    @Value( "${weather-stack.api-key}" )
    public void setApiKey(String apiKey) {

        Constants.API_KEY = apiKey;
    }

    @Value("${weather-stack.cache-name}")
    public void setWeatherCacheName(String cacheName) {
        Constants.WEATHER_CACHE_NAME = cacheName;
    }

}

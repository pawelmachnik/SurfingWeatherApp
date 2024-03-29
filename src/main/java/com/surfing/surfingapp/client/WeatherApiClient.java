package com.surfing.surfingapp.client;

import com.surfing.surfingapp.model.data.LocationWeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherApiClient {
    private final RestTemplate restTemplate;
    private final String weatherApiUrl;
    private final String apiKey;

    public WeatherApiClient(RestTemplate restTemplate,
                            @Value("${weather.api.url}") String weatherApiUrl,
                            @Value("${weather.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.weatherApiUrl = weatherApiUrl;
        this.apiKey = apiKey;
    }

    public LocationWeatherData getWeatherData(String city) {
        String apiUrl = weatherApiUrl + "?city=" + city + "&key=" + apiKey;
        return restTemplate.getForObject(apiUrl, LocationWeatherData.class);
    }
}


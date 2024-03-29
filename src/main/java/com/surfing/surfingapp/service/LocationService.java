package com.surfing.surfingapp.service;

import com.surfing.surfingapp.client.WeatherApiClient;
import com.surfing.surfingapp.exception.DataNotInRangeException;
import com.surfing.surfingapp.exception.WeatherNotFoundException;
import com.surfing.surfingapp.model.Location;
import com.surfing.surfingapp.model.data.LocationWeatherData;
import com.surfing.surfingapp.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final WeatherApiClient weatherApiClient;

    public Location getBestWeather(LocalDate date) {
        validDate(date);
        List<Location> locations = locationRepository.findAll();
        List<LocationWeatherData> locationWeatherData = locations.stream()
                .map(location -> weatherApiClient.getWeatherData(location.getName()))
                .toList();
        Optional<LocationWeatherData> bestWeatherPerLocation = findBestWeather(locationWeatherData, date);
        LocationWeatherData bestWeather = bestWeatherPerLocation.orElseThrow(WeatherNotFoundException::new);

        return Location.builder()
                .name(bestWeather.getCityName())
                .temperature(bestWeather.getWeatherOfDayData().get(0).getTemperature())
                .windSpeed(bestWeather.getWeatherOfDayData().get(0).getWindSpeed())
                .build();
    }

    private void validDate(LocalDate date) {
        LocalDate today = LocalDate.now();
        LocalDate maxAllowedDate = today.plusDays(15);

        if (date.isBefore(today) || date.isAfter(maxAllowedDate)) {
            throw new DataNotInRangeException();
        }
    }

    private Optional<LocationWeatherData> findBestWeather(List<LocationWeatherData> locationWeatherDataList, LocalDate requestedDate) {
        return locationWeatherDataList.stream()
                .map(location -> new LocationWeatherData(location.getCityName(),
                        location.getWeatherOfDayData().stream()
                                .filter(weather -> weather.getDate().equals(requestedDate))
                                .filter(weather -> areTheConditionsInRange(weather.getWindSpeed(), weather.getTemperature()))
                                .max(Comparator.comparingDouble(weather -> bestLocationCalculator(weather.getWindSpeed(), weather.getTemperature())))
                                .map(Collections::singletonList)
                                .orElse(Collections.emptyList())))
                .filter(location -> !location.getWeatherOfDayData().isEmpty())
                .max(Comparator.comparingDouble(location -> bestLocationCalculator(location.getWeatherOfDayData().get(0).getWindSpeed(), location.getWeatherOfDayData().get(0).getTemperature())));
    }

    private double bestLocationCalculator(double windSpeed, double temp) {
        return windSpeed * 3 + temp;
    }

    private boolean areTheConditionsInRange(double windSpeed, double temp) {
        return windSpeed >= 5.0 && windSpeed <= 18.0 && temp >= 5.0 && temp <= 35.0;
    }
}


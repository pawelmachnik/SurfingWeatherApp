package com.surfing.surfingapp.model.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationWeatherData {
    @JsonProperty("city_name")
    private String cityName;
    @JsonProperty("data")
    private List<WeatherOfDayData> weatherOfDayData;
}
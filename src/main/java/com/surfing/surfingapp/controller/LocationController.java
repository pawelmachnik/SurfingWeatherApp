package com.surfing.surfingapp.controller;

import com.surfing.surfingapp.model.Location;
import com.surfing.surfingapp.model.data.dto.LocationDto;
import com.surfing.surfingapp.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/best-location")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService localizationService;
    private final ModelMapper modelMapper;

    @GetMapping("/{date}")
    public ResponseEntity getBestLocation(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return new ResponseEntity(toDto(localizationService.getBestWeather(date)), HttpStatus.OK);
    }

    private LocationDto toDto(Location location) {
        return modelMapper.map(location, LocationDto.class);
    }
}

package com.surfing.surfingapp.exception.handler;

import com.surfing.surfingapp.exception.DataNotInRangeException;
import com.surfing.surfingapp.exception.WeatherNotFoundException;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotInRangeException.class)
    public ResponseEntity handleDataNotInRangeException() {
        return ResponseEntity.badRequest().body(new DataNotInRangeErrorDto("DATA_NOT_IN_RANGE"));
    }

    @ExceptionHandler(WeatherNotFoundException.class)
    public ResponseEntity handleWeatherNotFoundException() {
        return ResponseEntity.badRequest().body(new WeatherNotFoundErrorDto("WEATHER_NOT_FOUND"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataErrorDto("WRONG_DATA"));
    }

    @Value
    @Builder
    public static class DataNotInRangeErrorDto {
        private String code;
    }

    @Value
    @Builder
    public static class WeatherNotFoundErrorDto {
        private String code;
    }

    @Value
    @Builder
    public static class DataErrorDto {
        private String code;
    }
}

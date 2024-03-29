package com.surfing.surfingapp.exception;

import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Value
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataNotInRangeException extends IllegalArgumentException {
}


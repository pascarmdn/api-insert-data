package com.example.api_insert_data.util;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateUtil {
    public LocalTime stringTimeToLocalTome(String time, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalTime.parse(time, formatter);
    }
    public Long processTimeInterval(LocalTime start, LocalTime end) {
        Duration duration = Duration.between(start, end);
        return duration.getSeconds();
    }
}

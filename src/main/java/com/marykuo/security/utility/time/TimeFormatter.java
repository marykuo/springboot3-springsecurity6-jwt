package com.marykuo.security.utility.time;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class TimeFormatter {
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * yyyyMMdd
     */
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.BASIC_ISO_DATE;
    /**
     * yyyy-MM-dd
     */
    public static final DateTimeFormatter DATE_WITH_DASH_FORMATTER = DateTimeFormatter.ISO_DATE;
    /**
     * HHmmss
     */
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
    /**
     * HH:mm:ss
     */
    public static final DateTimeFormatter TIME_WITH_COLON_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    /**
     * JavaTimeModule with custom serializers for LocalDateTime, LocalDate, and LocalTime
     */
    public static final SimpleModule javaTimeModule = new JavaTimeModule()
            .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER))
            .addSerializer(LocalDate.class, new LocalDateSerializer(DATE_WITH_DASH_FORMATTER))
            .addSerializer(LocalTime.class, new LocalTimeSerializer(TIME_WITH_COLON_FORMATTER));
}

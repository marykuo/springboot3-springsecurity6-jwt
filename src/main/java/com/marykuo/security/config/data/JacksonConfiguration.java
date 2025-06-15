package com.marykuo.security.config.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.marykuo.security.utility.time.TimeFormatter.*;

@Configuration
@Slf4j
public class JacksonConfiguration {

    @Bean
    @Primary
    @Qualifier("objectMapper")
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) // 忽略未知欄位
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES) // 開啟 不區分大小寫
                .disable(SerializationFeature.INDENT_OUTPUT) // 開啟 Pretty print
                .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true)) // 開啟 BigDecimal 轉換(不會產生科學記號)
                .disable(DeserializationFeature.UNWRAP_ROOT_VALUE) // 關閉 顯示 classname
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) // 關閉 檢查未知(多餘)屬性(物件內沒定義的屬性)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // 關閉 時間的轉化格式(默認timestamp)，必須搭配setDateFormat()
                .setDateFormat(new StdDateFormat()); // yyyy-MM-dd

        // 設定日期格式
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_WITH_DASH_FORMATTER));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_WITH_DASH_FORMATTER));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(TIME_WITH_COLON_FORMATTER));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(TIME_WITH_COLON_FORMATTER));
        objectMapper.registerModule(javaTimeModule);

        log.debug("Initialize ObjectMapper(jsonMapper) OK.");
        return objectMapper;
    }

    /**
     * @return json converter
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(objectMapper());
        jsonConverter.setDefaultCharset(StandardCharsets.UTF_8);
        return jsonConverter;
    }
}

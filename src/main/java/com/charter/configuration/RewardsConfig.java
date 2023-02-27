package com.charter.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
public class RewardsConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setSerializationInclusion(JsonInclude.Include.NON_DEFAULT)
                .registerModule(new JavaTimeModule())
                .setDateFormat(df);
    }
}

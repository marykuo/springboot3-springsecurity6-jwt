package com.marykuo.security.adapter.in.api.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

import static com.marykuo.security.utility.time.TimeFormatter.javaTimeModule;

public abstract class Request implements Serializable {

    @Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper().registerModule(javaTimeModule);
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return this.getClass() + " toString() error";
        }
    }
}

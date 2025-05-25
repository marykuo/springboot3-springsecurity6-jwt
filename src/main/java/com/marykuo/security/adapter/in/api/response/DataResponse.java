package com.marykuo.security.adapter.in.api.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class DataResponse<T> extends BaseResponse {
    protected T data;

    public DataResponse(T data) {
        super(true, "200", "success");
        this.data = data;
    }

    public DataResponse(String message) {
        super(false, null, message);
        this.data = null;
    }

    public DataResponse(String code, String message) {
        super(false, code, message);
        this.data = null;
    }

    @Override
    public String toString() {
        try {
            return (new ObjectMapper()).writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return this.getClass() + " toString() error";
        }
    }
}

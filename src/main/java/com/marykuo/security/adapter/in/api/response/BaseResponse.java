package com.marykuo.security.adapter.in.api.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class BaseResponse {
    protected boolean result;
    protected String code;
    protected String message;

    public BaseResponse() {
        this.result = true;
        this.code = "200";
        this.message = "success";
    }

    public BaseResponse(String message) {
        this.result = false;
        this.code = null;
        this.message = message;
    }

    public BaseResponse(String code, String message) {
        this.result = false;
        this.code = code;
        this.message = message;
    }

    public BaseResponse(boolean result, String code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }

    public static BaseResponse success() {
        return new BaseResponse(true, "200", "success");
    }

    public static BaseResponse fail(String message) {
        return new BaseResponse(false, null, message);
    }

    public static BaseResponse fail(String code, String message) {
        return new BaseResponse(false, code, message);
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

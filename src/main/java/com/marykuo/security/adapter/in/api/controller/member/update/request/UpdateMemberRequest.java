package com.marykuo.security.adapter.in.api.controller.member.update.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class UpdateMemberRequest {
    private String firstName;
    private String lastName;
    private String password;

    @Override
    public String toString() {
        try {
            return (new ObjectMapper()).writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return this.getClass() + " toString() error";
        }
    }
}

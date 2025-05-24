package com.marykuo.security.adapter.in.api.controller.member.login.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private String token;
}

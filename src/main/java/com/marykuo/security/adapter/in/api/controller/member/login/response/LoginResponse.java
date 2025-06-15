package com.marykuo.security.adapter.in.api.controller.member.login.response;

import com.marykuo.security.adapter.in.api.response.Response;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse extends Response {
    private String token;
}

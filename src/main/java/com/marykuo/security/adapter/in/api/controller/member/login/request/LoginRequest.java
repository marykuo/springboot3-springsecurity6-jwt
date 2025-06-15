package com.marykuo.security.adapter.in.api.controller.member.login.request;

import com.marykuo.security.adapter.in.api.request.Request;
import lombok.Getter;

@Getter
public class LoginRequest extends Request {
    private String email;
    private String password;
}

package com.marykuo.security.adapter.in.api.controller.member.register.request;

import com.marykuo.security.adapter.in.api.request.Request;
import lombok.Getter;

@Getter
public class RegisterRequest extends Request {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

package com.marykuo.security.adapter.in.api.controller.member.update.request;

import com.marykuo.security.adapter.in.api.request.Request;
import lombok.Getter;

@Getter
public class UpdateMemberRequest extends Request {
    private String firstName;
    private String lastName;
    private String password;
}

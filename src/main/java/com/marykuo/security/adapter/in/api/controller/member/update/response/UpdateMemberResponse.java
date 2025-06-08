package com.marykuo.security.adapter.in.api.controller.member.update.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateMemberResponse {
    private String firstName;
    private String lastName;
}

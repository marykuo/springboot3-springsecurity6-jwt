package com.marykuo.security.adapter.in.api.controller.member.query.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuerySingleMemberResponse {
    private Integer memberId;
    private String firstName;
    private String lastName;
}

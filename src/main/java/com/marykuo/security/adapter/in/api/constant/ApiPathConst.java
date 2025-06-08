package com.marykuo.security.adapter.in.api.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiPathConst {
    public static final String ROOT_API = "/api";
    public static final String ROOT_PUBLIC = "/public";

    public static final String LOGIN = "/auth/login";
    public static final String REGISTER = "/auth/register";

    public static final String MEMBER_PAGINATION = "/member";
    public static final String MEMBER_SINGLE = "/member/{memberId}";
    public static final String MEMBER_UPDATE = "/member/{memberId}";
}

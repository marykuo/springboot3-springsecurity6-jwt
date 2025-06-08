package com.marykuo.security.adapter.in.api.controller.resource.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryResourceResponse {
    private Integer id;
    private String name;
}

package com.marykuo.security.adapter.in.api.controller.resource;

import com.marykuo.security.adapter.in.api.controller.resource.response.QueryResourceResponse;
import com.marykuo.security.adapter.in.api.response.DataResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.marykuo.security.adapter.in.api.constant.ApiPathConst.ROOT_PUBLIC;

@RestController
@RequestMapping(ROOT_PUBLIC)
@Tag(name = "Resource API", description = "no authentication required")
@RequiredArgsConstructor
public class QueryPublicResourceController {

    @GetMapping(value = "/v1/resource")
    public ResponseEntity<DataResponse<QueryResourceResponse>> resource() {
        return ResponseEntity.ok(new DataResponse<>(QueryResourceResponse.builder()
                .id(1)
                .name("Here is your public resource")
                .build()
        ));
    }
}

package com.marykuo.security.adapter.in.api.controller.resource;

import com.marykuo.security.adapter.in.api.controller.resource.response.QueryResourceResponse;
import com.marykuo.security.adapter.in.api.response.DataResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.marykuo.security.adapter.in.api.constant.ApiPathConst.ROOT_API;

@RestController
@RequestMapping(ROOT_API)
@Tag(name = "Resource API", description = "require authentication")
@RequiredArgsConstructor
public class QueryAuthorizationResourceController {

    @GetMapping(value = "/v1/resource")
    public ResponseEntity<DataResponse<QueryResourceResponse>> resource() {
        return ResponseEntity.ok(new DataResponse<>(QueryResourceResponse.builder()
                .id(2)
                .name("Here is an private resource")
                .build()
        ));
    }

    @GetMapping(value = "/v1/resource/admin")
    public ResponseEntity<DataResponse<QueryResourceResponse>> adminResource() {
        return ResponseEntity.ok(new DataResponse<>(QueryResourceResponse.builder()
                .id(3)
                .name("Here is an private admin resource")
                .build()
        ));
    }

    @GetMapping(value = "/v1/resource/user")
    public ResponseEntity<DataResponse<QueryResourceResponse>> userResource() {
        return ResponseEntity.ok(new DataResponse<>(QueryResourceResponse.builder()
                .id(4)
                .name("Here is your private resource")
                .build()
        ));
    }
}

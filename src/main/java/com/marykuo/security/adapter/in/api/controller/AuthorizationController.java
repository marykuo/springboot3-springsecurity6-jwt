package com.marykuo.security.adapter.in.api.controller;

import com.marykuo.security.adapter.in.api.response.DataResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Resource API", description = "require authentication")
@RequiredArgsConstructor
public class AuthorizationController {

    @GetMapping(value = "/v1/resource")
    public ResponseEntity<DataResponse<String>> resource() {
        return ResponseEntity.ok(new DataResponse<>("Here is an private resource"));
    }

    @GetMapping(value = "/v1/resource/admin")
    public ResponseEntity<DataResponse<String>> adminResource() {
        return ResponseEntity.ok(new DataResponse<>("Here is an private admin resource"));
    }

    @GetMapping(value = "/v1/resource/user")
    public ResponseEntity<DataResponse<String>> userResource() {
        return ResponseEntity.ok(new DataResponse<>("Here is your private resource"));
    }
}

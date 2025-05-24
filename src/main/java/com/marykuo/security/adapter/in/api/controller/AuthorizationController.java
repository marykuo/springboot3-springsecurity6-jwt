package com.marykuo.security.adapter.in.api.controller;

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
    public ResponseEntity<String> resource() {
        return ResponseEntity.ok("Here is your private resource");
    }
}

package com.marykuo.security.adapter.in.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@Tag(name = "Resource API", description = "no authentication required")
@RequiredArgsConstructor
public class PublicController {

    @GetMapping(value = "/v1/resource")
    public ResponseEntity<String> resource() {
        return ResponseEntity.ok("Here is your public resource");
    }
}

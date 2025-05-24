package com.marykuo.security.adapter.in.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health")
@RequiredArgsConstructor
public class HealthController {

    @GetMapping(value = "/")
    public ResponseEntity<String> heartbeat() {
        return ResponseEntity.ok("alive");
    }
}

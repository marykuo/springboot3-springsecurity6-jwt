package com.marykuo.security.adapter.in.api.controller.member.register;

import com.marykuo.security.adapter.in.api.controller.member.register.request.RegisterRequest;
import com.marykuo.security.adapter.in.api.response.BaseResponse;
import com.marykuo.security.service.member.register.RegisterService;
import com.marykuo.security.service.member.register.port.in.RegisterUseCase;
import com.marykuo.security.service.member.register.port.out.RegisterPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Register")
@RequiredArgsConstructor
@Slf4j
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping(value = "/v1/auth/register")
    public ResponseEntity<BaseResponse> register(@RequestBody RegisterRequest request) {
        log.debug("Register request: {}", request);

        RegisterPort registerPort = registerService.execute(
                RegisterUseCase.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .build()
        );

        return ResponseEntity.ok(BaseResponse.success());
    }
}

package com.marykuo.security.adapter.in.api.controller.member.register;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marykuo.security.adapter.in.api.controller.member.register.request.RegisterRequest;
import com.marykuo.security.adapter.in.api.response.BaseResponse;
import com.marykuo.security.usecase.member.register.RegisterService;
import com.marykuo.security.usecase.member.register.input.RegisterInput;
import com.marykuo.security.usecase.member.register.output.RegisterOutput;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.marykuo.security.adapter.in.api.constant.ApiPathConst.REGISTER;
import static com.marykuo.security.adapter.in.api.constant.ApiPathConst.ROOT_API;

@RestController
@RequestMapping(ROOT_API)
@Tag(name = "Register")
@RequiredArgsConstructor
@Slf4j
public class RegisterController {
    private final RegisterService registerService;
    private final ObjectMapper objectMapper;

    @PostMapping(value = "/v1" + REGISTER)
    public ResponseEntity<BaseResponse> register(@RequestBody RegisterRequest request) {
        log.debug("RegisterRequest: {}", request);

        RegisterOutput registerOutput = registerService.execute(
                RegisterInput.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .build()
        );

        return ResponseEntity.ok(BaseResponse.success());
    }
}

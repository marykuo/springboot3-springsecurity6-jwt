package com.marykuo.security.adapter.in.api.controller.member.login;

import com.marykuo.security.adapter.in.api.controller.member.login.request.LoginRequest;
import com.marykuo.security.adapter.in.api.controller.member.login.response.LoginResponse;
import com.marykuo.security.adapter.in.api.response.DataResponse;
import com.marykuo.security.adapter.out.database.entity.MemberEntity;
import com.marykuo.security.config.security.component.JwtService;
import com.marykuo.security.usecase.member.authentication.AuthenticationService;
import com.marykuo.security.usecase.member.authentication.input.AuthenticationInput;
import com.marykuo.security.usecase.member.authentication.output.AuthenticationOutput;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.marykuo.security.adapter.in.api.constant.ApiPathConst.LOGIN;
import static com.marykuo.security.adapter.in.api.constant.ApiPathConst.ROOT_API;

@RestController
@RequestMapping(ROOT_API)
@Tag(name = "Authentication")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping(value = "/v1" + LOGIN)
    public ResponseEntity<DataResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        log.debug("LoginRequest: {}", request);

        AuthenticationOutput authenticationOutput = authenticationService.execute(
                AuthenticationInput.builder()
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .build()
        );

        String jwtToken = jwtService.generateToken((MemberEntity) authenticationOutput.getMember());

        return ResponseEntity.ok(new DataResponse<>(
                LoginResponse.builder()
                        .token(jwtToken)
                        .build()
        ));
    }
}

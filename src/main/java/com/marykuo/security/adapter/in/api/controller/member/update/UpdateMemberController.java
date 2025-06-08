package com.marykuo.security.adapter.in.api.controller.member.update;

import com.marykuo.security.adapter.in.api.controller.member.update.request.UpdateMemberRequest;
import com.marykuo.security.adapter.in.api.controller.member.update.response.UpdateMemberResponse;
import com.marykuo.security.adapter.in.api.response.DataResponse;
import com.marykuo.security.service.member.update.UpdateMemberService;
import com.marykuo.security.service.member.update.port.in.UpdateMemberUseCase;
import com.marykuo.security.service.member.update.port.out.UpdateMemberPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Member")
@RequiredArgsConstructor
@Slf4j
public class UpdateMemberController {
    private final UpdateMemberService updateMemberService;

    @PutMapping(value = "/v1/member")
    public ResponseEntity<DataResponse<UpdateMemberResponse>> update(@RequestBody UpdateMemberRequest request) {
        log.debug("login: {}", request);

        // authenticate
        UpdateMemberPort updateMemberPort = updateMemberService.execute(
                UpdateMemberUseCase.builder()
                        .memberId(request.getMemberId())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .password(request.getPassword())
                        .build()
        );

        return ResponseEntity.ok(new DataResponse<>(
                UpdateMemberResponse.builder()
                        .firstName(updateMemberPort.getMember().getFirstName())
                        .lastName(updateMemberPort.getMember().getLastName())
                        .build()
        ));
    }
}

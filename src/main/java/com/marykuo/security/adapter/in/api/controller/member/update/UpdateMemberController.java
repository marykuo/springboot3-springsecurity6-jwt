package com.marykuo.security.adapter.in.api.controller.member.update;

import com.marykuo.security.adapter.in.api.controller.member.update.request.UpdateMemberRequest;
import com.marykuo.security.adapter.in.api.controller.member.update.response.UpdateMemberResponse;
import com.marykuo.security.adapter.in.api.response.DataResponse;
import com.marykuo.security.adapter.out.database.entity.MemberEntity;
import com.marykuo.security.domain.member.RoleEnum;
import com.marykuo.security.usecase.member.update.UpdateMemberService;
import com.marykuo.security.usecase.member.update.input.UpdateMemberInput;
import com.marykuo.security.usecase.member.update.output.UpdateMemberOutput;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

import static com.marykuo.security.adapter.in.api.constant.ApiPathConst.MEMBER_SINGLE;
import static com.marykuo.security.adapter.in.api.constant.ApiPathConst.ROOT_API;
import static com.marykuo.security.adapter.in.api.filter.JwtAuthenticationFilter.LOGIN_MEMBER;

@RestController
@RequestMapping(ROOT_API)
@Tag(name = "Member")
@RequiredArgsConstructor
@Slf4j
public class UpdateMemberController {
    private final UpdateMemberService updateMemberService;

    @PutMapping(value = "/v1" + MEMBER_SINGLE)
    public ResponseEntity<DataResponse<UpdateMemberResponse>> update(
            @RequestAttribute(LOGIN_MEMBER) MemberEntity loginMember,
            @PathVariable("memberId") Long memberId,
            @RequestBody UpdateMemberRequest request
    ) {
        log.debug("member[{}] update member[{}] with request: {}", loginMember.getMemberId(), memberId, request);

        if (loginMember.getRole() != RoleEnum.ADMIN && !loginMember.getMemberId().equals(memberId)) {
            throw new NoSuchElementException();
        }

        UpdateMemberOutput updateMemberOutput = updateMemberService.execute(
                UpdateMemberInput.builder()
                        .memberId(loginMember.getMemberId())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .password(request.getPassword())
                        .build()
        );

        return ResponseEntity.ok(new DataResponse<>(
                UpdateMemberResponse.builder()
                        .firstName(updateMemberOutput.getMember().getFirstName())
                        .lastName(updateMemberOutput.getMember().getLastName())
                        .build()
        ));
    }
}

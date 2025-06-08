package com.marykuo.security.adapter.in.api.controller.member.query;

import com.marykuo.security.adapter.in.api.controller.member.query.response.QuerySingleMemberResponse;
import com.marykuo.security.adapter.in.api.response.DataResponse;
import com.marykuo.security.adapter.out.database.MemberRepository;
import com.marykuo.security.adapter.out.database.entity.MemberEntity;
import com.marykuo.security.domain.member.RoleEnum;
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
public class QuerySingleMemberController {
    private final MemberRepository memberRepository;

    @GetMapping(value = "/v1" + MEMBER_SINGLE)
    public ResponseEntity<DataResponse<QuerySingleMemberResponse>> query(
            @RequestAttribute(LOGIN_MEMBER) MemberEntity loginMember,
            @PathVariable("memberId") Integer memberId
    ) {
        log.debug("member[{}] query member[{}]", loginMember.getMemberId(), memberId);

        if (loginMember.getRole() != RoleEnum.ADMIN && !loginMember.getMemberId().equals(memberId)) {
            throw new NoSuchElementException();
        }

        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(NoSuchElementException::new);

        return ResponseEntity.ok(new DataResponse<>(QuerySingleMemberResponse.builder()
                .memberId(memberEntity.getMemberId())
                .firstName(memberEntity.getFirstName())
                .lastName(memberEntity.getLastName())
                .build()
        ));
    }
}

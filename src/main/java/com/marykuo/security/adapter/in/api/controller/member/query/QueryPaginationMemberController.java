package com.marykuo.security.adapter.in.api.controller.member.query;

import com.marykuo.security.adapter.in.api.controller.member.query.response.QueryPaginationMemberResponse;
import com.marykuo.security.adapter.in.api.response.PaginationResponse;
import com.marykuo.security.adapter.out.database.MemberRepository;
import com.marykuo.security.adapter.out.database.entity.MemberEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.marykuo.security.adapter.in.api.constant.ApiPathConst.MEMBER_PAGINATION;
import static com.marykuo.security.adapter.in.api.constant.ApiPathConst.ROOT_API;
import static com.marykuo.security.adapter.in.api.filter.JwtAuthenticationFilter.LOGIN_MEMBER;

@RestController
@RequestMapping(ROOT_API)
@Tag(name = "Member")
@RequiredArgsConstructor
@Slf4j
public class QueryPaginationMemberController {
    private final MemberRepository memberRepository;

    @GetMapping(value = "/v1" + MEMBER_PAGINATION)
    public ResponseEntity<PaginationResponse<QueryPaginationMemberResponse>> query(
            @RequestAttribute(LOGIN_MEMBER) MemberEntity loginMember,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "pageOffset", defaultValue = "0") Integer pageOffset,
            @RequestParam(value = "sortBy", defaultValue = "memberId") String sortBy,
            @RequestParam(value = "isAscending", defaultValue = "true") boolean isAscending
    ) {
        log.debug("member[{}] query pagination member", loginMember.getMemberId());

        Page<MemberEntity> memberEntityPage = memberRepository.findAll(pageSize, pageOffset, sortBy, isAscending);

        return ResponseEntity.ok(new PaginationResponse<>(
                memberEntityPage.getContent().stream()
                        .map(QueryPaginationMemberResponse::mapper)
                        .toList(),
                memberEntityPage.getTotalElements(),
                memberEntityPage.getTotalPages(),
                pageOffset,
                pageSize
        ));
    }
}

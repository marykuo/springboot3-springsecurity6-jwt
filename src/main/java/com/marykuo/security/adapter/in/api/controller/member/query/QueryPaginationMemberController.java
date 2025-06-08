package com.marykuo.security.adapter.in.api.controller.member.query;

import com.marykuo.security.adapter.in.api.controller.member.query.response.QueryPaginationMemberResponse;
import com.marykuo.security.adapter.in.api.response.PaginationResponse;
import com.marykuo.security.adapter.out.database.MemberRepository;
import com.marykuo.security.adapter.out.database.entity.MemberEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Member")
@RequiredArgsConstructor
public class QueryPaginationMemberController {
    private final MemberRepository memberRepository;

    @GetMapping(value = "/v1/member")
    public ResponseEntity<PaginationResponse<QueryPaginationMemberResponse>> resource(
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "pageOffset", defaultValue = "0") Integer pageOffset,
            @RequestParam(value = "sortBy", defaultValue = "memberId") String sortBy,
            @RequestParam(value = "isAscending", defaultValue = "true") boolean isAscending
    ) {
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

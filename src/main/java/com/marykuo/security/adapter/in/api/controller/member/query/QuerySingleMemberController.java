package com.marykuo.security.adapter.in.api.controller.member.query;

import com.marykuo.security.adapter.in.api.controller.member.query.response.QuerySingleMemberResponse;
import com.marykuo.security.adapter.in.api.response.DataResponse;
import com.marykuo.security.adapter.out.database.MemberRepository;
import com.marykuo.security.adapter.out.database.entity.MemberEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@Tag(name = "Member")
@RequiredArgsConstructor
public class QuerySingleMemberController {
    private final MemberRepository memberRepository;

    @GetMapping(value = "/v1/member/{memberId}")
    public ResponseEntity<DataResponse<QuerySingleMemberResponse>> adminResource(
            @PathVariable("memberId") Integer memberId
    ) {
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

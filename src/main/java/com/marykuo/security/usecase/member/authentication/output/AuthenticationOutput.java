package com.marykuo.security.usecase.member.authentication.output;

import com.marykuo.security.domain.member.Member;
import com.marykuo.security.usecase.Output;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationOutput extends Output {
    private Member member;
}

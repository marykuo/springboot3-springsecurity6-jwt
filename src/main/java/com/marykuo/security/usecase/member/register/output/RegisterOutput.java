package com.marykuo.security.usecase.member.register.output;

import com.marykuo.security.domain.member.Member;
import com.marykuo.security.usecase.Output;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterOutput extends Output {
    private Member member;
}

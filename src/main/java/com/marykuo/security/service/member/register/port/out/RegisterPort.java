package com.marykuo.security.service.member.register.port.out;

import com.marykuo.security.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterPort {
    private Member member;
}

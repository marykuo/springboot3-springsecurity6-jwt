package com.marykuo.security.service.member.authentication.port.out;

import com.marykuo.security.adapter.out.database.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationPort {
    private MemberEntity member;
}

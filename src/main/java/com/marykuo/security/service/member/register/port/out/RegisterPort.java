package com.marykuo.security.service.member.register.port.out;

import com.marykuo.security.adapter.out.database.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterPort {
    private MemberEntity member;
}

package com.marykuo.security.service.member.update.port.out;

import com.marykuo.security.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateMemberPort {
    private Member member;
}

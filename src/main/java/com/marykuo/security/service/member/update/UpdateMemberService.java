package com.marykuo.security.service.member.update;

import com.marykuo.security.adapter.out.database.MemberRepository;
import com.marykuo.security.domain.member.Member;
import com.marykuo.security.service.member.update.port.in.UpdateMemberUseCase;
import com.marykuo.security.service.member.update.port.out.UpdateMemberPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateMemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UpdateMemberPort execute(UpdateMemberUseCase updateMemberUseCase) {
        log.info("UpdateMemberUseCase: {}", updateMemberUseCase);

        // validate input
        updateMemberUseCase.validate();

        // validate data
        Member member = memberRepository.findById(updateMemberUseCase.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        // execute
        member.setFirstName(updateMemberUseCase.getFirstName());
        member.setLastName(updateMemberUseCase.getLastName());
        member.setPassword(passwordEncoder.encode(updateMemberUseCase.getPassword()));
        memberRepository.save(member);

        return UpdateMemberPort.builder()
                .member(member)
                .build();
    }
}

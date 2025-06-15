package com.marykuo.security.usecase.member.update;

import com.marykuo.security.adapter.out.database.MemberRepository;
import com.marykuo.security.domain.member.Member;
import com.marykuo.security.usecase.member.update.input.UpdateMemberInput;
import com.marykuo.security.usecase.member.update.output.UpdateMemberOutput;
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
    public UpdateMemberOutput execute(UpdateMemberInput updateMemberInput) {
        log.info("UpdateMemberUseCase: {}", updateMemberInput);

        // validate input
        updateMemberInput.validate();

        // validate data
        Member member = memberRepository.findById(updateMemberInput.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        // execute
        member.setFirstName(updateMemberInput.getFirstName());
        member.setLastName(updateMemberInput.getLastName());
        member.setPassword(passwordEncoder.encode(updateMemberInput.getPassword()));
        memberRepository.save(member);

        return UpdateMemberOutput.builder()
                .member(member)
                .build();
    }
}

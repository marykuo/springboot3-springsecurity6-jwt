package com.marykuo.security.usecase.member.register;

import com.marykuo.security.adapter.out.database.MemberRepository;
import com.marykuo.security.domain.member.Member;
import com.marykuo.security.domain.member.RoleEnum;
import com.marykuo.security.usecase.member.register.input.RegisterInput;
import com.marykuo.security.usecase.member.register.output.RegisterOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterOutput execute(RegisterInput registerInput) {
        log.info("RegisterUseCase: {}", registerInput);

        // validate input
        registerInput.validate();

        // validate data
        if (memberRepository.findByEmail(registerInput.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered.");
        }

        // execute
        String encodedPassword = passwordEncoder.encode(registerInput.getPassword());
        // fixme: determine role based on firstName for demo purposes
        RoleEnum role = "admin".equalsIgnoreCase(registerInput.getFirstName()) ? RoleEnum.ADMIN : RoleEnum.USER;
        Member member = new Member(registerInput, encodedPassword, role);
        memberRepository.save(member);

        return RegisterOutput.builder()
                .member(member)
                .build();
    }
}

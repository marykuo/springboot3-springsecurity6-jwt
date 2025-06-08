package com.marykuo.security.service.member.register;

import com.marykuo.security.adapter.out.database.MemberRepository;
import com.marykuo.security.domain.member.Member;
import com.marykuo.security.domain.member.RoleEnum;
import com.marykuo.security.service.member.register.port.in.RegisterUseCase;
import com.marykuo.security.service.member.register.port.out.RegisterPort;
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

    public RegisterPort execute(RegisterUseCase registerUseCase) {
        log.info("RegisterUseCase: {}", registerUseCase);

        // validate input
        registerUseCase.validate();

        // validate data
        if (memberRepository.findByEmail(registerUseCase.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered.");
        }

        // execute
        Member member = Member.builder()
                .firstName(registerUseCase.getFirstName())
                .lastName(registerUseCase.getLastName())
                .email(registerUseCase.getEmail())
                .password(passwordEncoder.encode(registerUseCase.getPassword()))
                .role("admin".equalsIgnoreCase(registerUseCase.getLastName()) ? RoleEnum.ADMIN : RoleEnum.USER)
                .build();
        memberRepository.save(member);

        return RegisterPort.builder()
                .member(member)
                .build();
    }
}

package com.marykuo.security.service.member.authentication;

import com.marykuo.security.adapter.out.database.MemberRepository;
import com.marykuo.security.domain.member.Member;
import com.marykuo.security.service.member.authentication.port.in.AuthenticationUseCase;
import com.marykuo.security.service.member.authentication.port.out.AuthenticationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationPort execute(AuthenticationUseCase authenticationUseCase) {
        log.info("AuthenticationUseCase: {}", authenticationUseCase);

        // validate input
        authenticationUseCase.validate();

        // validate data
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationUseCase.getEmail(), authenticationUseCase.getPassword()));
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Invalid email or password.");
        }
        Member member = memberRepository.findByEmail(authenticationUseCase.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        return AuthenticationPort.builder()
                .member(member)
                .build();
    }
}

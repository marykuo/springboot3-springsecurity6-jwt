package com.marykuo.security.usecase.member.authentication;

import com.marykuo.security.adapter.out.database.MemberRepository;
import com.marykuo.security.domain.member.Member;
import com.marykuo.security.usecase.member.authentication.input.AuthenticationInput;
import com.marykuo.security.usecase.member.authentication.output.AuthenticationOutput;
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

    public AuthenticationOutput execute(AuthenticationInput authenticationInput) {
        log.info("AuthenticationUseCase: {}", authenticationInput);

        // validate input
        authenticationInput.validate();

        // validate data
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationInput.getEmail(), authenticationInput.getPassword()));
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Invalid email or password.");
        }
        Member member = memberRepository.findByEmail(authenticationInput.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        return AuthenticationOutput.builder()
                .member(member)
                .build();
    }
}

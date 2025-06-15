package com.marykuo.security.domain.member;

import com.marykuo.security.domain.DomainModel;
import com.marykuo.security.usecase.member.register.input.RegisterInput;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends DomainModel {
    protected Long memberId;

    protected String firstName;

    protected String lastName;

    protected String email;

    protected String password;

    protected RoleEnum role;

    protected LocalDateTime createdAt;

    protected LocalDateTime modifiedAt;

    public Member(RegisterInput registerInput, String encodedPassword, RoleEnum role) {
        this.firstName = registerInput.getFirstName();
        this.lastName = registerInput.getLastName();
        this.email = registerInput.getEmail();
        this.password = encodedPassword;
        this.role = role;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }
}

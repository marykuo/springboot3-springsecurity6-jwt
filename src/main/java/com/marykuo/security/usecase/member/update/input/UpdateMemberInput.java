package com.marykuo.security.usecase.member.update.input;

import com.marykuo.security.usecase.Input;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateMemberInput extends Input {
    private Long memberId;
    private String firstName;
    private String lastName;
    private String password;

    public void validate() {
        if (memberId == null) {
            throw new IllegalArgumentException("memberId cannot be null");
        }
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("firstName cannot be null or empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("lastName cannot be null or empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }
}

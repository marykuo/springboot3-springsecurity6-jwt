package com.marykuo.security.usecase.member.register.input;

import com.marykuo.security.usecase.Input;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterInput extends Input {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public void validate() {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }
}

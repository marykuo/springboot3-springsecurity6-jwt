package com.marykuo.security.adapter.out.database.entity;

import com.marykuo.security.domain.member.Member;
import com.marykuo.security.domain.member.RoleEnum;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "member")
public class MemberEntity extends Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_id_sequence")
    @SequenceGenerator(name = "member_id_sequence", sequenceName = "member_id_sequence", allocationSize = 1)
    public Integer getMemberId() {
        return super.memberId;
    }

    public String getFirstName() {
        return super.firstName;
    }

    public String getLastName() {
        return super.lastName;
    }

    @Column(unique = true)
    public String getEmail() {
        return super.email;
    }

    public String getPassword() {
        return super.password;
    }

    @Enumerated(EnumType.STRING)
    public RoleEnum getRole() {
        return super.role;
    }

    public MemberEntity() {
        super();
    }

    public MemberEntity(Member member) {
        super(member.getMemberId(), member.getFirstName(), member.getLastName(), member.getEmail(), member.getPassword(), member.getRole());
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + super.role.name()));
    }

    /**
     * unique identifier for the member
     */
    @Override
    @Transient
    public String getUsername() {
        return email;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }
}

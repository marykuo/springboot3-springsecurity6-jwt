package com.marykuo.security.adapter.out.database.impl;

import com.marykuo.security.adapter.out.database.MemberRepository;
import com.marykuo.security.adapter.out.database.entity.MemberEntity;
import com.marykuo.security.adapter.out.database.repository.MemberJpaRepository;
import com.marykuo.security.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final MemberJpaRepository memberJpaRepository;

    public MemberEntity save(Member member) {
        return memberJpaRepository.save(new MemberEntity(member));
    }

    public Optional<MemberEntity> findById(Long memberId) {
        return memberJpaRepository.findById(memberId);
    }

    public Optional<MemberEntity> findByEmail(String email) {
        return memberJpaRepository.findByEmail(email);
    }

    public Page<MemberEntity> findAll(
            Integer pageSize, Integer pageOffset, String sortBy, boolean isAscending
    ) {
        Sort sort = Sort.by(isAscending ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize, sort);

        return memberJpaRepository.findAll(pageRequest);
    }
}

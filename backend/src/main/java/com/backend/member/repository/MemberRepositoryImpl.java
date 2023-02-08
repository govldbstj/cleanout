package com.backend.member.repository;

import com.backend.member.domain.Member;
import com.backend.member.exception.MemberDuplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    @Override
    public void validateDuplication(String username) {
        memberJpaRepository.findByUsername(username)
                .orElseThrow(MemberDuplicationException::new);
    }
}

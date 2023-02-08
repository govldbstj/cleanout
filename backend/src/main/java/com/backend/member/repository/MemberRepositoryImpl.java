package com.backend.member.repository;

import com.backend.member.domain.Member;
import com.backend.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return memberJpaRepository.findByUsername(username);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberJpaRepository.findByEmail(email);
    }

    @Override
    public Member getByEmailAndPassword(String email, String password) {
        return memberJpaRepository.findByEmailAndPassword(email, password)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member getById(Long id) {
        return memberJpaRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public long count() {
        return memberJpaRepository.count();
    }
}

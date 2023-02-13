package com.backend.member.repository;

import com.backend.member.domain.Member;
import com.backend.member.exception.MemberNotFoundException;
import com.backend.member.exception.MemberNotMatchException;
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
    public Optional<Member> findByNickname(String username) {
        return memberJpaRepository.findByNickname(username);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberJpaRepository.findByEmail(email);
    }

    @Override
    public Optional<Member> findByNicknameAndEmail(String nickname, String email) {
        return memberJpaRepository.findByNicknameAndEmail(nickname, email);
    }

    @Override
    public Member getByNicknameAndEmail(String nickname, String email) {
        return memberJpaRepository.findByNicknameAndEmail(nickname, email)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member getByEmail(String email) {
        return memberJpaRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member getById(Long id) {
        return memberJpaRepository.findById(id)
                .orElseThrow(MemberNotMatchException::new);
    }

    @Override
    public long count() {
        return memberJpaRepository.count();
    }
}

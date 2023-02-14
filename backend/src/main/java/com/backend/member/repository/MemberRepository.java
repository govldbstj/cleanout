package com.backend.member.repository;

import com.backend.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNicknameAndEmail(String nickname, String email);

    Member getByNicknameAndEmail(String nickname, String email);

    Member getByEmail(String email);

    Member getById(Long id);

    long count();
}

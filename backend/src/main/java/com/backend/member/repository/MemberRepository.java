package com.backend.member.repository;

import com.backend.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findByUsername(String username);

    Optional<Member> findByEmail(String email);

    Member getByEmailAndPassword(String email, String password);

    Member getById(Long id);

    long count();
}

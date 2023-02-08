package com.backend.member.repository;

import com.backend.member.domain.Member;

public interface MemberRepository {

    void save(Member member);

    void validateDuplication(String username);
}

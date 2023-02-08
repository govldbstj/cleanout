package com.backend.member.service;

import com.backend.member.domain.Member;
import com.backend.member.dto.request.MemberSignup;
import com.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(MemberSignup memberSignup) {
        Member member = Member.getFromMemberSignup(memberSignup);
        memberRepository.save(member);
    }

    public void validateDuplication(MemberSignup memberSignup) {
        memberRepository.validateDuplication(memberSignup.getUsername());
    }
}

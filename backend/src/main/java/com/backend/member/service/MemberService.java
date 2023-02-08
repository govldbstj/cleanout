package com.backend.member.service;

import com.backend.member.domain.Member;
import com.backend.member.domain.MemberSession;
import com.backend.member.dto.request.MemberLogin;
import com.backend.member.dto.request.MemberSignup;
import com.backend.member.exception.MemberDuplicationException;
import com.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

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
        Optional<Member> byUsername = memberRepository.findByUsername(memberSignup.getUsername());
        Optional<Member> byEmail = memberRepository.findByEmail(memberSignup.getEmail());
        if (byUsername.isPresent() || byEmail.isPresent()) {
            throw new MemberDuplicationException();
        }
    }

    public MemberSession getMemberSession(MemberLogin memberLogin) {
        Member member = memberRepository.getByEmailAndPassword(memberLogin.getEmail(), memberLogin.getPassword());
        MemberSession memberSession = MemberSession.getFromMember(member);
        return memberSession;
    }

    public void makeSessionForMemberSession(MemberSession memberSession, HttpServletRequest httpServletRequest) {
        memberSession.makeSession(httpServletRequest);
    }
}

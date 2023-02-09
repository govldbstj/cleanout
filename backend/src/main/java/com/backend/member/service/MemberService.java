package com.backend.member.service;

import com.backend.kakao.dto.KakaoLogin;
import com.backend.kakao.dto.KakaoSignup;
import com.backend.member.domain.Member;
import com.backend.member.domain.MemberSession;
import com.backend.member.dto.request.MemberLogin;
import com.backend.member.dto.request.MemberSignup;
import com.backend.member.dto.request.MemberUpdate;
import com.backend.member.exception.MemberDuplicationException;
import com.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @Transactional
    public void kakaoSignup(KakaoSignup kakaoSignup) {
        Member member = Member.getFromKakaoSignup(kakaoSignup);
        memberRepository.save(member);
    }

    public void validateDuplication(MemberSignup memberSignup) {
        Optional<Member> byNickname = memberRepository.findByNickname(memberSignup.getNickname());
        Optional<Member> byEmail = memberRepository.findByEmail(memberSignup.getEmail());
        if (byNickname.isPresent() || byEmail.isPresent()) {
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

    @Transactional
    public Member update(MemberSession memberSession, MemberUpdate memberUpdate) {
        Member member = memberRepository.getById(memberSession.getId());
        member.update(memberUpdate);
        return member;
    }

    public void logout(MemberSession memberSession, HttpServletRequest httpServletRequest) {
        memberSession.invalidateSession(httpServletRequest);
    }

    public boolean needToSignup(HashMap<String, Object> userInfo) {
        Optional<Member> optionalMember =
                memberRepository.findByNicknameAndEmail((String) userInfo.get("nickname"),
                        (String) userInfo.get("email"));
        if (optionalMember.isPresent()) {
            return false;
        }
        return true;
    }

    public Member getByKakaoLogin(KakaoLogin kakaoLogin) {
        return memberRepository.getByNicknameAndEmail(kakaoLogin.getNickname(), kakaoLogin.getEmail());
    }
}

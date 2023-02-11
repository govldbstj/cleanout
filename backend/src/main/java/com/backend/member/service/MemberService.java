package com.backend.member.service;

import com.backend.member.domain.Member;
import com.backend.member.domain.MemberSession;
import com.backend.member.dto.request.MemberLogin;
import com.backend.member.dto.request.MemberSignup;
import com.backend.member.dto.request.MemberUpdate;
import com.backend.member.exception.MemberDuplicationException;
import com.backend.member.exception.MemberNotMatchException;
import com.backend.member.repository.MemberRepository;
import com.backend.util.enumerated.SignupType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(MemberSignup memberSignup) {
        Member member = Member.getFromMemberSignup(memberSignup);
        member.encodePassword(passwordEncoder);
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
        Member member = memberRepository.getByEmail(memberLogin.getEmail());
        MemberSession memberSession = MemberSession.getFromMember(member);
        return memberSession;
    }

    public void validateMatch(MemberLogin memberLogin) {
        Member member = memberRepository.getByEmail(memberLogin.getEmail());
        if (!passwordEncoder.matches(memberLogin.getPassword(), member.getPassword())) {
            throw new MemberNotMatchException();
        }
    }

    public void makeSessionForMemberSession(MemberSession memberSession, HttpServletRequest httpServletRequest) {
        memberSession.makeSession(httpServletRequest);
    }

    @Transactional
    public Member update(MemberSession memberSession, MemberUpdate memberUpdate) {
        Member member = memberRepository.getById(memberSession.getId());
        member.update(memberUpdate, passwordEncoder);
        return member;
    }

    public void logout(MemberSession memberSession, HttpServletRequest httpServletRequest) {
        if (memberSession.getSignupType() == SignupType.KAKAO) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + memberSession.getAccessToken());
            HttpEntity<String> entity = new HttpEntity<>(headers);

            restTemplate.exchange(
                    "https://kapi.kakao.com/v1/user/logout",
                    HttpMethod.POST,
                    entity,
                    String.class
            );
        }
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
}

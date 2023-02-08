package com.backend.member.domain;

import com.backend.member.dto.request.MemberSignup;
import com.backend.waste.domain.Waste;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String username;
    private String address;

    private String phoneNumber;

    @OneToMany(mappedBy = "member")
    private List<Waste> wastes = new ArrayList<>();

    @Builder
    public Member(String email, String password, String username, String address, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static Member getFromMemberSignup(MemberSignup memberSignup) {
        return Member.builder()
                .email(memberSignup.getEmail())
                .password(memberSignup.getPassword())
                .username(memberSignup.getUsername())
                .address(memberSignup.getAddress())
                .phoneNumber(memberSignup.getPhoneNumber())
                .build();
    }
}

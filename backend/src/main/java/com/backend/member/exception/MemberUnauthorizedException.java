package com.backend.member.exception;

import com.backend.global.error.UnauthorizedException;

public class MemberUnauthorizedException extends UnauthorizedException {

    private static final String MESSAGE = "로그인 후 이용해주세요";

    public MemberUnauthorizedException() {
        super(MESSAGE);
    }
}

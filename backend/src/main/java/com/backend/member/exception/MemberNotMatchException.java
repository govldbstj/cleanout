package com.backend.member.exception;

import com.backend.global.error.NotMatchException;

public class MemberNotMatchException extends NotMatchException {

    private static final String MESSAGE = "회원 정보가 일치하지 않습니다";

    public MemberNotMatchException() {
        super(MESSAGE);
    }
}

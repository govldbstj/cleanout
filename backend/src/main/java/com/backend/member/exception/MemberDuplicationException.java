package com.backend.member.exception;

import com.backend.global.error.DuplicationException;

public class MemberDuplicationException extends DuplicationException {

    private static final String MESSAGE = "이미 존재하는 회원입니다";

    public MemberDuplicationException() {
        super(MESSAGE);
    }
}

package com.backend.waste.exception;

import com.backend.global.error.DuplicationException;

public class UniqueDuplicationException extends DuplicationException {

    private static final String MESSAGE = "이미 존재하는 고유문자입니다.";

    public UniqueDuplicationException(){super(MESSAGE);}
}

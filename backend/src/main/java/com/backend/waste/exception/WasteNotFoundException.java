package com.backend.waste.exception;

import com.backend.global.error.NotFoundException;

public class WasteNotFoundException extends NotFoundException {

    private static final String MESSAGE = "해당 예약을 찾을 수 없습니다.";

    public WasteNotFoundException() {
        super(MESSAGE);
    }
}

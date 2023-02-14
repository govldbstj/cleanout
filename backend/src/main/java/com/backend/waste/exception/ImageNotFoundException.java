package com.backend.waste.exception;

import com.backend.global.error.NotFoundException;

public class ImageNotFoundException extends NotFoundException {

    private static final String MESSAGE = "해당 이미지를 찾을 수 없습니다.";

    public ImageNotFoundException() {
        super(MESSAGE);
    }
}

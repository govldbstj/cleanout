package com.backend.global.error;

import lombok.Getter;

@Getter
public abstract class NotFoundException extends RuntimeException {

    private final String statusCode = "404";
    private String message;

    public NotFoundException(String message) {
        this.message = message;
    }
}

package com.backend.waste.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Test {
    private List<String> image;

    @Builder
    public Test(List<String> image) {
        this.image = image;
    }
}

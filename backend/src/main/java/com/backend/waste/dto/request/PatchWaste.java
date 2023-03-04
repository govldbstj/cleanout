package com.backend.waste.dto.request;

import lombok.Builder;
import lombok.Getter;


@Getter
public class PatchWaste {
    private String wasteName;
    private int price;

    public PatchWaste() {
    }

    @Builder
    public PatchWaste(String wasteName, int price) {
        this.wasteName = wasteName;
        this.price = price;
    }
}

package com.backend.waste.dto.request;

import lombok.Builder;
import lombok.Getter;


@Getter
public class PatchWaste {
    private String unique;
    private String wasteName;
    private int price;

    @Builder
    public PatchWaste(String unique, String wasteName, int price) {
        this.unique = unique;
        this.wasteName = wasteName;
        this.price = price;
    }
}

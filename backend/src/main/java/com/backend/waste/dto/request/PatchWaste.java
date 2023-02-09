package com.backend.waste.dto.request;

import lombok.Builder;
import lombok.Getter;


@Getter
public class PatchWaste {
    private String imageName;
    private String wasteName;
    private int price;

    @Builder
    public PatchWaste(String imageName, String wasteName, int price) {
        this.imageName = imageName;
        this.wasteName = wasteName;
        this.price = price;
    }
}

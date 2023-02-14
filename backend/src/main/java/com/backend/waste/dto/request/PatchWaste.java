package com.backend.waste.dto.request;

import lombok.Builder;
import lombok.Getter;


@Getter
public class PatchWaste {
    private Long wasteIdx;
    private String wasteName;
    private int price;

    @Builder
    public PatchWaste(Long wasteIdx, String wasteName, int price) {
        this.wasteIdx = wasteIdx;
        this.wasteName = wasteName;
        this.price = price;
    }
}

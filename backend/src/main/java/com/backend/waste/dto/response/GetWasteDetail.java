package com.backend.waste.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetWasteDetail {
    private String memberName;
    private String address;
    private String wasteName;
    private int price;
    private String status;

    @Builder

    public GetWasteDetail(String memberName, String address, String wasteName, int price, String status) {
        this.memberName = memberName;
        this.address = address;
        this.wasteName = wasteName;
        this.price = price;
        this.status = status;
    }
}

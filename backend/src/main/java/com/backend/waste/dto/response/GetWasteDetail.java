package com.backend.waste.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GetWasteDetail {
    private String memberName;
    private String address;
    private String wasteName;
    private int price;
    private String status;
    private String imageType;
    private byte[] images;

    @Builder
    public GetWasteDetail(String memberName, String address, String wasteName, int price, String status,String fileExtension, byte[] image) {
        this.memberName = memberName;
        this.address = address;
        this.wasteName = wasteName;
        this.price = price;
        this.status = status;
        this.imageType = fileExtension;
        this.images = image;
    }
}

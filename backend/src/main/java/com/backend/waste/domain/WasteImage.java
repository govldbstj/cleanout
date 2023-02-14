package com.backend.waste.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class WasteImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "waste_id")
    private Waste waste;

    private String imageName;

    @Builder
    public WasteImage(Waste waste, String imageName){
        this.imageName = imageName;
        this.waste = waste;
    }

    public static WasteImage createImage(Waste waste, String image) {
        return WasteImage.builder()
                .imageName(image)
                .waste(waste)
                .build();
    }


}

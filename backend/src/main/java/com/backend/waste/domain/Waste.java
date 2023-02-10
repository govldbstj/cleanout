package com.backend.waste.domain;

import com.backend.member.domain.Member;
import com.backend.waste.dto.response.GetWasteBrief;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Waste {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "waste_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    // FetchType.LAZY는 지연로딩으로, member 클래스가 Proxy 객체가 되며, 멤버를 실제로 조회하는 시점에(주소조회가 되겠다) 쿼리가 나간다.
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "img_name")
    private String imageName;

    private int price;
    @Column(name = "waste_name")
    private String name;
    @Column(name = "is_collected")
    private boolean isCollected;

    @Column(name = "enrolled_date")
    private LocalDateTime enrolledDate;
    @Column(name = "collected_date")
    private LocalDateTime collectedDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "collector_id")
    private Collector collector;

    @Builder
    public Waste(Member member, String imgName, LocalDateTime localDateTime, boolean isCollected) {
        this.member = member;
        this.imageName = imgName;
        this.enrolledDate = localDateTime;
        this.isCollected = isCollected;
    }

    public static Waste getWasteFromPostImage(Member member, String imageName) {
        return Waste.builder()
                .member(member)
                .imgName(imageName)
                .localDateTime(LocalDateTime.now())
                .isCollected(false)
                .build();
    }

    public void update(String wasteName, int price) {
        this.name = wasteName;
        this.price = price;
    }
}

package com.backend.waste.domain;

import com.backend.collector.domain.Collector;
import com.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private String image;

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

    private String uniqueStr;

    @Builder
    public Waste(Member member, String image, LocalDateTime localDateTime, boolean isCollected, String unique) {
        this.member = member;
        this.image = image;
        this.enrolledDate = localDateTime;
        this.isCollected = isCollected;
        this.uniqueStr = unique;
    }

    public static Waste createWaste(Member member, String image) {
        return Waste.builder()
                .member(member)
                .localDateTime(LocalDateTime.now())
                .image(image)
                .isCollected(false)
                .build();
    }

    public void update(String wasteName, int price) {
        this.name = wasteName;
        this.price = price;
    }

    public void matchCollector(Collector collector, LocalDateTime date) {
        this.collector = collector;
        this.collectedDate = date;
    }

    public void collected(Collector collector) {
        this.isCollected = true;
        this.collectedDate = LocalDateTime.now();
        this.collector = collector;
    }
}

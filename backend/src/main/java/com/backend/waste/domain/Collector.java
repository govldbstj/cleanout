package com.backend.waste.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Collector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collector_id")
    private Long id;

    @Column(name = "collector_name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "collector")
    private List<Waste> wastes = new ArrayList<>();
}

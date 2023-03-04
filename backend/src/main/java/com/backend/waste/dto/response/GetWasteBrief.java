package com.backend.waste.dto.response;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class GetWasteBrief {
    private Long id;
    private LocalDate date;
    private String name;
    private String status;


    public GetWasteBrief(Long id, String name, LocalDateTime date, String status) {
        this.id = id;
        this.date = date.toLocalDate();
        this.name = name;
        this.status = status;
    }
}

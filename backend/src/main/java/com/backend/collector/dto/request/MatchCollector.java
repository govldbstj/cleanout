package com.backend.collector.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MatchCollector {
    private String atTime;

    public MatchCollector(){}

    @Builder
    public MatchCollector(String time) {
        this.atTime = time;
    }
}

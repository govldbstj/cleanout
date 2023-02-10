package com.backend.collector.dto.request;

import com.backend.collector.domain.Collector;
import lombok.Getter;

@Getter
public class PostCollector {
    private String collectorName;
    private String phoneNumber;

    public Collector toCollectorEntity() {
        return Collector.builder()
                .name(this.getCollectorName())
                .phoneNumber(this.getPhoneNumber())
                .build();
    }
}

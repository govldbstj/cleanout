package com.backend.collector.service;

import com.backend.collector.domain.Collector;
import com.backend.collector.dto.request.PostCollector;
import com.backend.collector.repository.CollectorJpaRepository;
import com.backend.waste.domain.Waste;
import com.backend.waste.exception.WasteNotFoundException;
import com.backend.waste.repository.WasteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CollectorService {
    private final CollectorJpaRepository collectorJpaRepository;
    private final WasteJpaRepository wasteJpaRepository;


    public Collector createCollector(PostCollector postCollector) {
        Collector collector = postCollector.toCollectorEntity();
        collectorJpaRepository.save(collector);
        return collector;
    }

    @Transactional
    public void matchCollector(Long wasteIdx, Long collectorIdx, String date) {
        int dateRange = 19;
        System.out.println(date);
        LocalDateTime collectedDate = LocalDateTime.parse(date.substring(0,dateRange),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Waste waste = wasteJpaRepository.findById(wasteIdx).orElseThrow(WasteNotFoundException::new);
        Collector collector = collectorJpaRepository.getById(collectorIdx);
        waste.matchCollector(collector,collectedDate);
    }
}

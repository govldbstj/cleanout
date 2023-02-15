package com.backend.collector.service;

import com.backend.collector.domain.Collector;
import com.backend.collector.dto.request.PostCollector;
import com.backend.collector.repository.CollectorJpaRepository;
import com.backend.collector.repository.CollectorRepository;
import com.backend.waste.domain.Waste;
import com.backend.waste.repository.WasteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CollectorService {
    private final CollectorRepository collectorRepository;
    private final WasteRepository wasteRepository;


    @Transactional
    public Collector createCollector(PostCollector postCollector) {
        Collector collector = postCollector.toCollectorEntity();
        collectorRepository.save(collector);
        return collector;
    }

    @Transactional
    public void matchCollector(Long wasteIdx, Long collectorIdx, String date) {
        int dateRange = 19;
        LocalDateTime collectedDate = LocalDateTime.parse(date.substring(0,dateRange),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Waste waste = wasteRepository.getById(wasteIdx);
        Collector collector = collectorRepository.getById(collectorIdx);
        waste.matchCollector(collector,collectedDate);
    }

    @Transactional
    public void collectWaste(Long wasteIdx, Long collectorIdx) {
        Waste waste = wasteRepository.getById(wasteIdx);
        Collector collector = collectorRepository.getById(collectorIdx);
        waste.collected(collector);
    }
}

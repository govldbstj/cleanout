package com.backend.collector.service;

import com.backend.collector.domain.Collector;
import com.backend.collector.dto.request.PostCollector;
import com.backend.collector.repository.CollectorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CollectorService {
    private final CollectorJpaRepository collectorJpaRepository;


    public Collector createCollector(PostCollector postCollector) {
        Collector collector = postCollector.toCollectorEntity();
        collectorJpaRepository.save(collector);
        return collector;
    }
}

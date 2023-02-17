package com.backend.collector.repository;

import com.backend.collector.domain.Collector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CollectorRepositoryImpl implements CollectorRepository{

    private final CollectorJpaRepository collectorJpaRepository;

    @Override
    public Collector getById(Long collectorIdx) {
        return collectorJpaRepository.getById(collectorIdx);
    }

    @Override
    public void save(Collector collector) {
        collectorJpaRepository.save(collector);
    }
}

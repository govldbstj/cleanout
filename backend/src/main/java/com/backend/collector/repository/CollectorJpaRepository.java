package com.backend.collector.repository;

import com.backend.collector.domain.Collector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectorJpaRepository extends JpaRepository<Collector, Long> {

    Collector getById(Long collectorIdx);
}

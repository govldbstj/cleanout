package com.backend.collector.repository;

import com.backend.collector.domain.Collector;

public interface CollectorRepository {

    Collector getById(Long collectorIdx);

    void save(Collector collector);
}

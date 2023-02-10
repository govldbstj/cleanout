package com.backend.collector.controller;

import com.backend.collector.domain.Collector;
import com.backend.collector.dto.request.PostCollector;
import com.backend.collector.service.CollectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/collector-management")
public class CollectorController {
    private final CollectorService collectorService;

    @PostMapping("")
    public ResponseEntity<Long> createCollector(@RequestBody PostCollector postCollector){
        Collector collector = collectorService.createCollector(postCollector);
        return ResponseEntity.ok(collector.getId());
    }
}

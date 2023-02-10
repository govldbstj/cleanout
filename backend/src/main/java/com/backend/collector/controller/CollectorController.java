package com.backend.collector.controller;

import com.backend.collector.domain.Collector;
import com.backend.collector.dto.request.MatchCollector;
import com.backend.collector.dto.request.PostCollector;
import com.backend.collector.service.CollectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/collector-management")
public class CollectorController {
    private final CollectorService collectorService;

    @PostMapping("")
    public ResponseEntity<Long> createCollector(@RequestBody PostCollector postCollector) {
        Collector collector = collectorService.createCollector(postCollector);
        return ResponseEntity.ok(collector.getId());
    }

    @PostMapping("/{wasteIdx}/{collectorIdx}")
    public ResponseEntity<Void> matchCollector(@PathVariable("wasteIdx") Long wasteIdx,
                                               @PathVariable("collectorIdx") Long collectorIdx,
                                               @RequestBody MatchCollector matchCollector){
        collectorService.matchCollector(wasteIdx, collectorIdx, matchCollector.getDate());
        return ResponseEntity.ok().build();
    }
}

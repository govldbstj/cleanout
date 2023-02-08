package com.backend.waste.controller;

import com.backend.waste.service.WasteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WasteController {

    private final WasteService wasteService;
}

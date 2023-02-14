package com.backend.waste.repository;

import com.backend.waste.domain.WasteImage;

public interface ImageRepository {

    WasteImage save(WasteImage wasteImage);
}

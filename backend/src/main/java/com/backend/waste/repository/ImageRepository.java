package com.backend.waste.repository;

import com.backend.waste.domain.Waste;
import com.backend.waste.domain.WasteImage;

import java.util.List;

public interface ImageRepository {

    WasteImage save(WasteImage wasteImage);

    List<WasteImage> getImagesByWaste(Waste waste);
}

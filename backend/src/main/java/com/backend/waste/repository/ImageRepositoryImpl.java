package com.backend.waste.repository;

import com.backend.waste.domain.Waste;
import com.backend.waste.domain.WasteImage;
import com.backend.waste.exception.ImageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ImageRepositoryImpl implements ImageRepository{

    private final ImageJpaRepository imageJpaRepository;

    @Override
    public WasteImage save(WasteImage wasteImage) {
        return imageJpaRepository.save(wasteImage);
    }

    @Override
    public WasteImage getImageByWaste(Waste waste) {
        return imageJpaRepository.findByWaste(waste).orElseThrow(ImageNotFoundException::new);
    }
}

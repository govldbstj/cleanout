package com.backend.waste.repository;

import com.backend.waste.domain.WasteImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ImageRepositoryImpl implements ImageRepository{

    private final ImageJpaRepository imageJpaRepository;

    @Override
    public WasteImage save(WasteImage wasteImage) {
        return imageJpaRepository.save(wasteImage);
    }
}

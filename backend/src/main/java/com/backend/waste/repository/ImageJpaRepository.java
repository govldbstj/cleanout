package com.backend.waste.repository;

import com.backend.waste.domain.Waste;
import com.backend.waste.domain.WasteImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageJpaRepository extends JpaRepository<WasteImage, Long> {
    Optional<WasteImage> findByWaste(Waste waste);
}

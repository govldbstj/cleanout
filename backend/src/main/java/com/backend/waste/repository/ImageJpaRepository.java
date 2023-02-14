package com.backend.waste.repository;

import com.backend.waste.domain.WasteImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageJpaRepository extends JpaRepository<WasteImage, Long> {
}

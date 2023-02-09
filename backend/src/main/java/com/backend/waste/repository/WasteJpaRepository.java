package com.backend.waste.repository;

import com.backend.waste.domain.Waste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteJpaRepository extends JpaRepository<Waste, Long> {

    Waste findByImageName(String imageName);
}

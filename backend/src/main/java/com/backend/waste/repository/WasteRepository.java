package com.backend.waste.repository;

import com.backend.member.domain.Member;
import com.backend.waste.domain.Waste;

import java.util.List;
import java.util.Optional;

public interface WasteRepository {

    Waste save(Waste waste);

    Optional<Waste> findById(Long wasteIdx);

    List<Waste> getWasteList(Member member);

    Waste getById(Long wasteIdx);

    long count();
}

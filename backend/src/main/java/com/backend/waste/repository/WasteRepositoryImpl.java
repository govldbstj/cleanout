package com.backend.waste.repository;

import com.backend.member.domain.Member;
import com.backend.waste.domain.Waste;
import com.backend.waste.exception.WasteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class WasteRepositoryImpl implements WasteRepository {

    private final WasteJpaRepository wasteJpaRepository;

    @Override
    public Waste save(Waste waste) { return wasteJpaRepository.save(waste); }

    @Override
    public Optional<Waste> findById(Long wasteIdx) {
        return Optional.empty();
    }

    @Override
    public List<Waste> getWasteList(Member member) {
        return wasteJpaRepository.findAllByMemberOrderByEnrolledDateDesc(member);
    }

    @Override
    public Waste getById(Long wasteIdx) {
        return wasteJpaRepository.findById(wasteIdx).orElseThrow(WasteNotFoundException::new);
    }

    @Override
    public long count() {
        return wasteJpaRepository.count();
    }
}

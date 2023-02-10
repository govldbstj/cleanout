package com.backend.waste.repository;

import com.backend.member.domain.Member;
import com.backend.waste.domain.Waste;
import com.backend.waste.dto.response.GetWasteBrief;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WasteJpaRepository extends JpaRepository<Waste, Long> {

    Waste findByImageName(String imageName);

    List<Waste> findAllByMemberOrderByEnrolledDateDesc(Member member);
}

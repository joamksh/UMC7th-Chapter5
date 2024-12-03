package org.example.spring.repository;

import org.example.spring.domain.enums.MissionStatus;
import org.example.spring.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Page<MemberMission> findAllByMemberIdAndStatus(Long memberId, MissionStatus status, Pageable pageable);

    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);
}

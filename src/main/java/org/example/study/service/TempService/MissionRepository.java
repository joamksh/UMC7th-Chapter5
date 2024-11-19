package org.example.study.service.TempService;

import org.example.study.domain.Mission;
import org.example.study.domain.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findByStatus(MissionStatus status, Pageable pageable);
}
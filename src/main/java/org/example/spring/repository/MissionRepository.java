package org.example.spring.repository;

import org.example.spring.domain.Mission;
import org.example.spring.domain.Store;
import org.example.spring.dto.MissionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findAllByStore(Store store, Pageable pageable);
}

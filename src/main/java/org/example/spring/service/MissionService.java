package org.example.spring.service;

import org.example.spring.dto.MissionRequestDTO;
import org.example.spring.dto.MissionResponseDTO;
import org.springframework.data.domain.Page;

public interface MissionService {
    MissionResponseDTO addMission(MissionRequestDTO requestDTO);
    Page<MissionResponseDTO> getMissionsByStore(Long storeId, Integer page);

}

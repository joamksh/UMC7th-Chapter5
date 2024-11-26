package org.example.spring.service;

import org.example.spring.dto.MissionRequestDTO;
import org.example.spring.dto.MissionResponseDTO;

public interface MissionService {
    MissionResponseDTO addMission(MissionRequestDTO requestDTO);
}

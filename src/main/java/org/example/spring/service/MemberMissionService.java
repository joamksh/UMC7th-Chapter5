package org.example.spring.service;

import org.example.spring.dto.MemberMissionRequestDTO;
import org.example.spring.dto.MemberMissionResponseDTO;
import org.springframework.data.domain.Page;

public interface MemberMissionService {
    MemberMissionResponseDTO addMemberMission(MemberMissionRequestDTO requestDTO);
    Page<MemberMissionResponseDTO> getOngoingMissions(MemberMissionRequestDTO.OngoingMissionsRequestDTO requestDTO);
}

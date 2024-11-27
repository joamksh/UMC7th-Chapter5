package org.example.spring.service;

import org.example.spring.dto.MemberMissionRequestDTO;
import org.example.spring.dto.MemberMissionResponseDTO;

public interface MemberMissionService {
    MemberMissionResponseDTO addMemberMission(MemberMissionRequestDTO requestDTO);
}

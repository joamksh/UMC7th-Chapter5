package org.example.spring.converter;

import org.example.spring.domain.Mission;
import org.example.spring.domain.Store;
import org.example.spring.dto.MissionRequestDTO;
import org.example.spring.dto.MissionResponseDTO;

public class MissionConverter {

    public static Mission toEntity(MissionRequestDTO requestDTO, Store store) {
        return Mission.builder()
                .reward(requestDTO.getReward())
                .deadline(requestDTO.getDeadline())
                .missionSpec(requestDTO.getMissionSpec())
                .store(store)
                .build();
    }

    public static MissionResponseDTO toResponseDTO(Mission mission) {
        return MissionResponseDTO.builder()
                .id(mission.getId())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .storeName(mission.getStore().getName())
                .build();
    }
}

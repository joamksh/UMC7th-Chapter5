package org.example.spring.converter;

import org.example.spring.domain.Member;
import org.example.spring.domain.Mission;
import org.example.spring.domain.enums.MissionStatus;
import org.example.spring.domain.mapping.MemberMission;
import org.example.spring.dto.MemberMissionRequestDTO;
import org.example.spring.dto.MemberMissionResponseDTO;

public class MemberMissionConverter {

    public static MemberMission toEntity(MemberMissionRequestDTO requestDTO, Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }

    public static MemberMissionResponseDTO toResponseDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.builder()
                .id(memberMission.getId())
                .memberName(memberMission.getMember().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .status(memberMission.getStatus())
                .reward(memberMission.getMission().getReward())
                .storeName(memberMission.getMission().getStore().getName())
                .build();
    }
}

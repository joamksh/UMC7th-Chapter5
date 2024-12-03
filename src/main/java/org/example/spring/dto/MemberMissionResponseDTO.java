package org.example.spring.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.spring.domain.enums.MissionStatus;

@Getter
@Builder
public class MemberMissionResponseDTO {

    private Long id;
    private String memberName;
    private String missionSpec;
    private MissionStatus status;
}
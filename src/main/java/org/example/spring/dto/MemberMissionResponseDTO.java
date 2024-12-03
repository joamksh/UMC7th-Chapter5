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
    private Integer reward; // 보상 금액
    private String storeName; // 미션이 속한 가게 이름
}
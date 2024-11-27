package org.example.spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberMissionRequestDTO {

    @NotNull
    private Long memberId;

    @NotNull
    private Long missionId;
}

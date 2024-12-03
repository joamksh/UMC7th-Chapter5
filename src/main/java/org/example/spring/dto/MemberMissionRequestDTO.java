package org.example.spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.spring.domain.enums.MissionStatus;

@Getter
public class MemberMissionRequestDTO {

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "미션 ID는 필수입니다.")
    private Long missionId;

    @Getter
    @Setter
    public static class OngoingMissionsRequestDTO {
        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;

        @NotNull(message = "페이지 번호는 필수입니다.")
        private Integer page;
    }
}
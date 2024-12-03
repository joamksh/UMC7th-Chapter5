package org.example.spring.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.example.spring.validation.annotation.ValidMissionChallenge;

@Getter
@ValidMissionChallenge // Custom Annotation for mission challenge validation
public class MemberMissionRequestDTO {

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "미션 ID는 필수입니다.")
    private Long missionId;
}
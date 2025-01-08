package org.example.spring.dto;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MissionRequestDTO {

    @NotNull(message = "가게 ID는 필수입니다.")
    private Long storeId;

    @NotNull(message = "리워드는 필수입니다.")
    @Min(1)
    private Integer reward;

    @Future(message = "마감일은 미래여야 합니다.")
    private LocalDate deadline;

    @NotBlank(message = "미션 설명은 필수입니다.")
    private String missionSpec;
}


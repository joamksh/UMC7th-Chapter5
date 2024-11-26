package org.example.spring.dto;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MissionRequestDTO {

    @NotNull
    private Long storeId;

    @NotNull
    @Min(1)
    private Integer reward;

    @NotNull
    private LocalDate deadline;

    @NotBlank
    private String missionSpec;
}


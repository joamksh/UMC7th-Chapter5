package org.example.spring.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MissionResponseDTO {

    private Long id;
    private Integer reward;
    private LocalDate deadline;
    private String missionSpec;
    private String storeName;
}

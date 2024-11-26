package umc.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MissionWithStoreAndStatusDto {
    private Long missionId;
    private Integer reward;
    private String missionSpec;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private MissionStatus status;
    private Long memberId;
    private String storeName;
}

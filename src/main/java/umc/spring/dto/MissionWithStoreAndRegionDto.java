package umc.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MissionWithStoreAndRegionDto {
    private Long missionId;
    private String missionSpec;
    private int reward;
    private String storeName;
    private String regionName;
}
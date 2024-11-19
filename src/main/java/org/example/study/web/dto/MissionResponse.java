package org.example.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.study.domain.MissionStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MissionResponse {

    private List<MissionDTO> missions;
    private int totalPages;
    private int currentPage;
    private int pageSize;
    private long totalItems;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissionDTO {
        private Long id;
        private String title;
        private MissionStatus status;
        private LocalDateTime createdAt;
    }
}
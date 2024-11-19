package org.example.study.service.TempService;

import org.example.study.web.dto.MissionResponse;

public interface MissionQueryService {
    MissionResponse getMissions(String status, int page, int size);
}
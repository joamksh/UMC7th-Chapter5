package org.example.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.study.apiPayload.ApiResponse;
import org.example.study.service.TempService.MissionQueryService;
import org.example.study.web.dto.MissionResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionQueryService missionQueryService;

    @GetMapping
    public ApiResponse<MissionResponse> getMissions(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        MissionResponse missionResponse = missionQueryService.getMissions(status, page, size);
        return ApiResponse.onSuccess(missionResponse);
    }
}

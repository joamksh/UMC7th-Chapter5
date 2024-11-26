package org.example.spring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring.dto.MissionRequestDTO;
import org.example.spring.dto.MissionResponseDTO;
import org.example.spring.response.ApiResponse;
import org.example.spring.service.MissionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @PostMapping
    public ApiResponse<MissionResponseDTO> addMission(@RequestBody @Valid MissionRequestDTO requestDTO) {
        MissionResponseDTO responseDTO = missionService.addMission(requestDTO);
        return ApiResponse.onSuccess(responseDTO);
    }
}

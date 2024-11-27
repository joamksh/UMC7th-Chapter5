package org.example.spring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring.dto.MemberMissionRequestDTO;
import org.example.spring.dto.MemberMissionResponseDTO;
import org.example.spring.response.ApiResponse;
import org.example.spring.service.MemberMissionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @PostMapping
    public ApiResponse<MemberMissionResponseDTO> addMemberMission(@RequestBody @Valid MemberMissionRequestDTO requestDTO) {
        MemberMissionResponseDTO responseDTO = memberMissionService.addMemberMission(requestDTO);
        return ApiResponse.onSuccess(responseDTO);
    }
}

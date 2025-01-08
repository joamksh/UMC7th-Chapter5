package org.example.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring.dto.MemberMissionRequestDTO;
import org.example.spring.dto.MemberMissionResponseDTO;
import org.example.spring.response.ApiResponse;
import org.example.spring.service.MemberMissionService;
import org.example.spring.validation.annotation.CheckPage;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @PostMapping
    @Operation(summary = "미션 등록 API", description = "회원이 특정 미션에 도전합니다.")
    public ApiResponse<MemberMissionResponseDTO> addMemberMission(@RequestBody @Valid MemberMissionRequestDTO requestDTO) {
        MemberMissionResponseDTO responseDTO = memberMissionService.addMemberMission(requestDTO);
        return ApiResponse.onSuccess(responseDTO);
    }

    @GetMapping("/{memberId}/ongoing")
    @Operation(summary = "진행 중인 미션 목록 API", description = "회원의 진행 중인 미션을 조회합니다.")
    public ApiResponse<Page<MemberMissionResponseDTO>> getOngoingMissions(
            @Parameter(description = "회원 ID", required = true)
            @PathVariable Long memberId,
            @Parameter(description = "페이지 번호 (1 이상)", required = true)
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        MemberMissionRequestDTO.OngoingMissionsRequestDTO requestDTO = new MemberMissionRequestDTO.OngoingMissionsRequestDTO();
        requestDTO.setMemberId(memberId);
        requestDTO.setPage(page);

        Page<MemberMissionResponseDTO> ongoingMissions = memberMissionService.getOngoingMissions(requestDTO);
        return ApiResponse.onSuccess(ongoingMissions);
    }

    @PutMapping("/{memberMissionId}/complete")
    @Operation(summary = "미션 상태 업데이트 API", description = "특정 회원 미션의 상태를 완료로 변경합니다.")
    public ApiResponse<MemberMissionResponseDTO> updateMissionStatus(
            @PathVariable Long memberMissionId
    ) {
        MemberMissionRequestDTO.UpdateStatusRequestDTO requestDTO = new MemberMissionRequestDTO.UpdateStatusRequestDTO();
        requestDTO.setMemberMissionId(memberMissionId);

        MemberMissionResponseDTO responseDTO = memberMissionService.updateMissionStatus(requestDTO);
        return ApiResponse.onSuccess(responseDTO);
    }
}

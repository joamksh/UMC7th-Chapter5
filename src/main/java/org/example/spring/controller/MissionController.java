package org.example.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring.dto.MissionRequestDTO;
import org.example.spring.dto.MissionResponseDTO;
import org.example.spring.response.ApiResponse;
import org.example.spring.service.MissionService;
import org.example.spring.validation.annotation.CheckPage;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/store/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션 목록을 조회하며 페이징을 지원합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "가게를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameter(name = "storeId", description = "가게 ID (PathVariable)", required = true)
    public ApiResponse<Page<MissionResponseDTO>> getMissionsByStore(
            @PathVariable Long storeId,
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        Page<MissionResponseDTO> missions = missionService.getMissionsByStore(storeId, page);
        return ApiResponse.onSuccess(missions);
    }
}

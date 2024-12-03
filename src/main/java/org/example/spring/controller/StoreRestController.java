package org.example.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring.converter.StoreConverter;
import org.example.spring.dto.StoreRequestDTO;
import org.example.spring.dto.StoreResponseDTO;
import org.example.spring.response.ApiResponse;
import org.example.spring.service.StoreCommandService;
import org.example.spring.service.StoreQueryService;
import org.example.spring.validation.annotation.ExistStore;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "406", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page") Integer page
    ) {
        var reviewList = storeQueryService.getReviewList(storeId, page); // 데이터 조회
        var reviewPreViewListDTO = StoreConverter.reviewPreViewListDTO(reviewList); // DTO 변환
        return ApiResponse.onSuccess(reviewPreViewListDTO); // 응답 반환
    }

    @PostMapping
    public ApiResponse<StoreResponseDTO.StoreDetailDto> createStore(@RequestBody @Valid StoreRequestDTO requestDTO) {
        StoreResponseDTO.StoreDetailDto store = storeCommandService.createStore(requestDTO);
        return ApiResponse.onSuccess(store);
    }
}


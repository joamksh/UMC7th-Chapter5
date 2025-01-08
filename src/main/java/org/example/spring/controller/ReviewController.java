package org.example.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring.dto.ReviewRequestDTO;
import org.example.spring.dto.ReviewResponseDTO;
import org.example.spring.response.ApiResponse;
import org.example.spring.service.ReviewService;
import org.example.spring.validation.annotation.CheckPage;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;


    @PostMapping
    @Operation(summary = "리뷰 작성 API")
    public ApiResponse<ReviewResponseDTO> addReview(@RequestBody @Valid ReviewRequestDTO requestDTO) {
        ReviewResponseDTO responseDTO = reviewService.addReview(requestDTO);
        return ApiResponse.onSuccess(responseDTO);
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "멤버가 작성한 리뷰 조회 API", description = "특정 멤버가 작성한 리뷰를 조회합니다. 페이징 처리가 포함됩니다.")
    public ApiResponse<Page<ReviewResponseDTO>> getReviewsByMember(
            @PathVariable Long memberId,
            @RequestParam(name = "page") Integer page
    ) {
        Page<ReviewResponseDTO> reviews = reviewService.getReviewsByMember(memberId, page);
        return ApiResponse.onSuccess(reviews);
    }

}

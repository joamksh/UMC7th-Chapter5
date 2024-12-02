package org.example.spring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring.dto.ReviewRequestDTO;
import org.example.spring.dto.ReviewResponseDTO;
import org.example.spring.response.ApiResponse;
import org.example.spring.service.ReviewCommandServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewCommandServiceImpl reviewCommandService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO> addReview(@RequestBody @Valid ReviewRequestDTO requestDTO) {
        ReviewResponseDTO response = reviewCommandService.addReview(requestDTO);
        return ApiResponse.onSuccess(response);
    }
}

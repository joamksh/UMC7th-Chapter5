package org.example.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring.domain.Review;
import org.example.spring.dto.ReviewRequestDTO;
import org.example.spring.dto.ReviewResponseDTO;
import org.example.spring.dto.StoreResponseDTO;
import org.example.spring.response.ApiResponse;
import org.example.spring.service.ReviewCommandServiceImpl;
import org.example.spring.validation.annotation.ExistStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

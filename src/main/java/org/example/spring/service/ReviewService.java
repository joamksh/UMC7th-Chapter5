package org.example.spring.service;

import org.example.spring.dto.ReviewRequestDTO;
import org.example.spring.dto.ReviewResponseDTO;
import org.springframework.data.domain.Page;

public interface ReviewService {
    ReviewResponseDTO addReview(ReviewRequestDTO requestDTO);
    Page<ReviewResponseDTO> getReviewsByMember(Long memberId, Integer page);
}
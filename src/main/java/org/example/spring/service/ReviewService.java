package org.example.spring.service;

import org.example.spring.dto.ReviewRequestDTO;
import org.example.spring.dto.ReviewResponseDTO;

public interface ReviewService {
    ReviewResponseDTO addReview(ReviewRequestDTO requestDTO);
}
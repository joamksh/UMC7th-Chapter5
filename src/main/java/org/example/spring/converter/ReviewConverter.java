package org.example.spring.converter;

import org.example.spring.domain.Member;
import org.example.spring.domain.Review;
import org.example.spring.domain.Store;
import org.example.spring.dto.ReviewRequestDTO;
import org.example.spring.dto.ReviewResponseDTO;

public class ReviewConverter {

    public static Review toEntity(ReviewRequestDTO requestDTO, Member member, Store store) {
        return Review.builder()
                .title(requestDTO.getTitle())
                .score(requestDTO.getScore())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResponseDTO toResponseDTO(Review review) {
        return ReviewResponseDTO.builder()
                .id(review.getId())
                .title(review.getTitle())
                .score(review.getScore())
                .memberName(review.getMember().getName())
                .storeName(review.getStore().getName())
                .build();
    }
}
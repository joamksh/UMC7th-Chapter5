package org.example.spring.converter;

import org.example.spring.domain.Member;
import org.example.spring.domain.Review;
import org.example.spring.domain.Store;
import org.example.spring.dto.ReviewRequestDTO;
import org.example.spring.dto.ReviewResponseDTO;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO requestDTO, Member member, Store store) {
        return Review.builder()
                .title(requestDTO.getTitle())
                .score(requestDTO.getScore())
                .body(requestDTO.getBody())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResponseDTO toResponseDTO(Review review) {
        return ReviewResponseDTO.builder()
                .id(review.getId())
                .title(review.getTitle())
                .score(review.getScore())
                .storeName(review.getStore().getName())
                .memberName(review.getMember().getName())
                .build();
    }
}

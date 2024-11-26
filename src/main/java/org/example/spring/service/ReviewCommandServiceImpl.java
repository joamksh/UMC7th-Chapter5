package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.converter.ReviewConverter;
import org.example.spring.domain.Member;
import org.example.spring.domain.Review;
import org.example.spring.domain.Store;
import org.example.spring.dto.ReviewRequestDTO;
import org.example.spring.dto.ReviewResponseDTO;
import org.example.spring.repository.MemberRepository;
import org.example.spring.repository.ReviewRepository;
import org.example.spring.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public ReviewResponseDTO addReview(ReviewRequestDTO requestDTO) {
        Member member = memberRepository.findById(requestDTO.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Store store = storeRepository.findById(requestDTO.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("Store not found"));

        Review review = Review.builder()
                .title(requestDTO.getTitle())
                .score(requestDTO.getScore())
                .member(member)
                .store(store)
                .build();

        review = reviewRepository.save(review);

        return ReviewConverter.toResponseDTO(review);
    }
}

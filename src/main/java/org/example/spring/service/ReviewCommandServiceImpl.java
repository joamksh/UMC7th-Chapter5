package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.converter.ReviewConverter;
import org.example.spring.domain.Member;
import org.example.spring.domain.Review;
import org.example.spring.domain.Store;
import org.example.spring.dto.ReviewRequestDTO;
import org.example.spring.dto.ReviewResponseDTO;
import org.example.spring.exception.ErrorStatus;
import org.example.spring.exception.ReviewHandler;
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

    @Transactional
    public ReviewResponseDTO addReview(ReviewRequestDTO requestDTO) {
        Member member = memberRepository.findById(requestDTO.getMemberId())
                .orElseThrow(() -> new ReviewHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(requestDTO.getStoreId())
                .orElseThrow(() -> new ReviewHandler(ErrorStatus.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(requestDTO, member, store);
        review = reviewRepository.save(review);

        return ReviewConverter.toResponseDTO(review);
    }
}

package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.converter.ReviewConverter;
import org.example.spring.domain.Member;
import org.example.spring.domain.Review;
import org.example.spring.domain.Store;
import org.example.spring.dto.ReviewRequestDTO;
import org.example.spring.dto.ReviewResponseDTO;
import org.example.spring.exception.CustomException;
import org.example.spring.exception.ErrorStatus;
import org.example.spring.repository.MemberRepository;
import org.example.spring.repository.ReviewRepository;
import org.example.spring.repository.StoreRepository;
import org.example.spring.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public ReviewResponseDTO addReview(ReviewRequestDTO requestDTO) {
        // 회원 조회
        Member member = memberRepository.findById(requestDTO.getMemberId())
                .orElseThrow(() -> new CustomException(ErrorStatus.MEMBER_NOT_FOUND));

        // 가게 조회
        Store store = storeRepository.findById(requestDTO.getStoreId())
                .orElseThrow(() -> new CustomException(ErrorStatus.STORE_NOT_FOUND));

        // Review 엔티티 생성 및 저장
        Review review = ReviewConverter.toEntity(requestDTO, member, store);
        Review savedReview = reviewRepository.save(review);

        // 저장된 Review를 DTO로 변환 후 반환
        return ReviewConverter.toResponseDTO(savedReview);
    }
}
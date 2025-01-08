package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.domain.Review;
import org.example.spring.domain.Store;
import org.example.spring.repository.ReviewRepository;
import org.example.spring.repository.StoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게를 찾을 수 없습니다."));

        // 특정 storeId에 대한 리뷰 리스트를 페이지로 가져오기
        return reviewRepository.findAllByStore(store, PageRequest.of(page - 1, 2)); // page는 1부터 시작
    }
}
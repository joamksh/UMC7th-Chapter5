package org.example.spring.service;

import org.example.spring.domain.Review;
import org.springframework.data.domain.Page;

public interface StoreQueryService {
    Page<Review> getReviewList(Long StoreId, Integer page);
}

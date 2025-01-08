package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public boolean existsById(Long storeId) {
        return storeRepository.existsById(storeId);
    }
}

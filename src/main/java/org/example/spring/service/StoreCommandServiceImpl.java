package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.converter.StoreConverter;
import org.example.spring.domain.Region;
import org.example.spring.domain.Store;
import org.example.spring.dto.StoreRequestDTO;
import org.example.spring.dto.StoreResponseDTO;
import org.example.spring.repository.RegionRepository;
import org.example.spring.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public StoreResponseDTO.StoreDetailDto createStore(StoreRequestDTO requestDTO) {
        Region region = regionRepository.findById(requestDTO.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("Region not found"));

        Store store = Store.builder()
                .name(requestDTO.getName())
                .address(requestDTO.getAddress())
                .region(region)
                .score(0F) // Default score
                .build();

        store = storeRepository.save(store);

        return StoreConverter.toStoreDetailDto(store);
    }
}
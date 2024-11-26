package org.example.spring.converter;

import org.example.spring.domain.Store;
import org.example.spring.dto.StoreResponseDTO;

public class StoreConverter {

    public static StoreResponseDTO.StoreDetailDto toStoreDetailDto(Store store) {
        return StoreResponseDTO.StoreDetailDto.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .regionName(store.getRegion().getName())
                .score(store.getScore())
                .build();
    }
}
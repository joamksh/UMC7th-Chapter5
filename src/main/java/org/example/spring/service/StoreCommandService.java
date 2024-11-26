package org.example.spring.service;

import org.example.spring.dto.StoreRequestDTO;
import org.example.spring.dto.StoreResponseDTO;

public interface StoreCommandService {
    StoreResponseDTO.StoreDetailDto createStore(StoreRequestDTO requestDTO);
}
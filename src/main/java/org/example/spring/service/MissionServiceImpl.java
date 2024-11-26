package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.converter.MissionConverter;
import org.example.spring.domain.Mission;
import org.example.spring.domain.Store;
import org.example.spring.dto.MissionRequestDTO;
import org.example.spring.dto.MissionResponseDTO;
import org.example.spring.exception.CustomException;
import org.example.spring.exception.ErrorStatus;
import org.example.spring.repository.MissionRepository;
import org.example.spring.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public MissionResponseDTO addMission(MissionRequestDTO requestDTO) {
        Store store = storeRepository.findById(requestDTO.getStoreId())
                .orElseThrow(() -> new CustomException(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toEntity(requestDTO, store);
        mission = missionRepository.save(mission);

        return MissionConverter.toResponseDTO(mission);
    }
}
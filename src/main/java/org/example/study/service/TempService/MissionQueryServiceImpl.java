package org.example.study.service.TempService;

import lombok.RequiredArgsConstructor;
import org.example.study.domain.Mission;
import org.example.study.domain.MissionStatus;
import org.example.study.web.dto.MissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public MissionResponse getMissions(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Mission> missionPage;

        if (status == null) {
            missionPage = missionRepository.findAll(pageable);
        } else {
            try {
                MissionStatus missionStatus = MissionStatus.valueOf(status.toUpperCase()); // 수정됨
                missionPage = missionRepository.findByStatus(missionStatus, pageable);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid status value: " + status); // 수정됨
            }
        }

        List<MissionResponse.MissionDTO> missions = missionPage.getContent().stream()
                .map(mission -> new MissionResponse.MissionDTO(
                        mission.getId(),
                        mission.getTitle(),
                        mission.getStatus(),
                        mission.getCreatedAt()))
                .collect(Collectors.toList());

        return new MissionResponse(missions, missionPage.getTotalPages(), page, size, missionPage.getTotalElements());
    }
}
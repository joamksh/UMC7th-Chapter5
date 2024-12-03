package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.converter.MemberMissionConverter;
import org.example.spring.domain.Member;
import org.example.spring.domain.Mission;
import org.example.spring.domain.mapping.MemberMission;
import org.example.spring.dto.MemberMissionRequestDTO;
import org.example.spring.dto.MemberMissionResponseDTO;
import org.example.spring.exception.ErrorStatus;
import org.example.spring.exception.MissionHandler;
import org.example.spring.repository.MemberMissionRepository;
import org.example.spring.repository.MemberRepository;
import org.example.spring.repository.MissionRepository;
import org.example.spring.service.MemberMissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberMissionServiceImpl implements MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public MemberMissionResponseDTO addMemberMission(MemberMissionRequestDTO requestDTO) {
        Member member = memberRepository.findById(requestDTO.getMemberId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(requestDTO.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        // 중복 체크
        boolean exists = memberMissionRepository.existsByMemberIdAndMissionId(requestDTO.getMemberId(), requestDTO.getMissionId());
        if (exists) {
            throw new MissionHandler(ErrorStatus.MISSION_ALREADY_CHALLENGED);
        }

        MemberMission memberMission = MemberMissionConverter.toEntity(requestDTO, member, mission);
        memberMission = memberMissionRepository.save(memberMission);

        return MemberMissionConverter.toResponseDTO(memberMission);
    }
}

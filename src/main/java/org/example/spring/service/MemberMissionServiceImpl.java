package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.converter.MemberMissionConverter;
import org.example.spring.domain.Member;
import org.example.spring.domain.enums.MissionStatus;
import org.example.spring.domain.mapping.MemberMission;
import org.example.spring.dto.MemberMissionRequestDTO;
import org.example.spring.dto.MemberMissionResponseDTO;
import org.example.spring.exception.ErrorStatus;
import org.example.spring.exception.MemberMissionHandler;
import org.example.spring.repository.MemberMissionRepository;
import org.example.spring.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberMissionServiceImpl implements MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MemberMissionResponseDTO addMemberMission(MemberMissionRequestDTO requestDTO) {
        // Validate member existence
        Member member = memberRepository.findById(requestDTO.getMemberId())
                .orElseThrow(() -> new MemberMissionHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // Check for duplicate missions
        boolean exists = memberMissionRepository.existsByMemberIdAndMissionId(
                requestDTO.getMemberId(),
                requestDTO.getMissionId()
        );
        if (exists) {
            throw new MemberMissionHandler(ErrorStatus.MISSION_ALREADY_CHALLENGED);
        }

        // Convert and save MemberMission
        MemberMission memberMission = MemberMissionConverter.toEntity(requestDTO, member, null);
        memberMission = memberMissionRepository.save(memberMission);

        return MemberMissionConverter.toResponseDTO(memberMission);
    }

    @Override
    public Page<MemberMissionResponseDTO> getOngoingMissions(MemberMissionRequestDTO.OngoingMissionsRequestDTO requestDTO) {
        // Validate page number
        if (requestDTO.getPage() < 1) {
            throw new MemberMissionHandler(ErrorStatus.PAGE_INDEX_INVALID);
        }

        // Fetch ongoing missions
        Page<MemberMission> ongoingMissions = memberMissionRepository.findAllByMemberIdAndStatus(
                requestDTO.getMemberId(),
                MissionStatus.CHALLENGING, // Pass the correct enum type
                PageRequest.of(requestDTO.getPage() - 1, 10)
        );

        // Convert to response DTO
        return ongoingMissions.map(MemberMissionConverter::toResponseDTO);
    }
}

package org.example.spring.service;

import org.example.spring.domain.Member;
import org.example.spring.dto.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}

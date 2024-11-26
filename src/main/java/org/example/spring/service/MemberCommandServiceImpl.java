package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.converter.MemberConverter;
import org.example.spring.converter.MemberPreferConverter;
import org.example.spring.domain.FoodCategory;
import org.example.spring.domain.Member;
import org.example.spring.domain.mapping.MemberPrefer;
import org.example.spring.dto.MemberRequestDTO;
import org.example.spring.exception.ErrorStatus;
import org.example.spring.exception.FoodCategoryHandler;
import org.example.spring.repository.FoodCategoryRepository;
import org.example.spring.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);

        // FoodCategory 조회
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(categoryId -> foodCategoryRepository.findById(categoryId)
                        .orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND)))
                .collect(Collectors.toList());

        // MemberPrefer 변환
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        // Member와 MemberPrefer 연결
        memberPreferList.forEach(memberPrefer -> memberPrefer.setMember(newMember));
        newMember.setMemberPreferList(memberPreferList);

        // 저장
        return memberRepository.save(newMember);
    }
}
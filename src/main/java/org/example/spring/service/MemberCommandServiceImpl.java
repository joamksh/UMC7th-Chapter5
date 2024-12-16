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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {
        try {
            System.out.println("회원가입 요청 처리 시작: " + request);

            Member newMember = MemberConverter.toMember(request);
            System.out.println("변환된 Member 엔티티: " + newMember);

            newMember.encodePassword(passwordEncoder.encode(request.getPassword()));

            // 선호 카테고리 조회
            List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                    .map(categoryId -> foodCategoryRepository.findById(categoryId)
                            .orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND)))
                    .collect(Collectors.toList());
            System.out.println("조회된 선호 카테고리: " + foodCategoryList);

            // MemberPrefer 변환
            List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);
            memberPreferList.forEach(memberPrefer -> memberPrefer.setMember(newMember));
            newMember.setMemberPreferList(memberPreferList);

            // Member 저장
            Member savedMember = memberRepository.save(newMember);
            System.out.println("저장된 Member: " + savedMember);

            return savedMember;

        } catch (Exception e) {
            System.err.println("회원가입 처리 중 예외 발생: " + e.getMessage());
            throw new RuntimeException("회원 가입 중 오류 발생: " + e.getMessage(), e);
        }
    }
}

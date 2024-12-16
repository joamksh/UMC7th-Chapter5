package org.example.spring.converter;

import org.example.spring.domain.Member;
import org.example.spring.domain.enums.Gender;
import org.example.spring.dto.MemberRequestDTO;

import java.util.ArrayList;

public class MemberConverter {

    public static Member toMember(MemberRequestDTO.JoinDto request) {
        Gender gender = null;
        if (request.getGender() != null) {
            switch (request.getGender()) {
                case 1:
                    gender = Gender.MALE;
                    break;
                case 2:
                    gender = Gender.FEMALE;
                    break;
                case 3:
                    gender = Gender.NONE;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid gender value");
            }
        }

        System.out.println("Gender 변환 완료: " + gender);

        return Member.builder()
                .name(request.getName())
                .gender(gender)
                .email(request.getEmail())
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .password(request.getPassword())
                .role(request.getRole())
                .memberPreferList(new ArrayList<>())
                .build();
    }
}

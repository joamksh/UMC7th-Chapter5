package org.example.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.example.spring.dto.MemberMissionRequestDTO;
import org.example.spring.repository.MemberMissionRepository;
import org.example.spring.validation.annotation.ValidMissionChallenge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionChallengeValidator implements ConstraintValidator<ValidMissionChallenge, MemberMissionRequestDTO> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(MemberMissionRequestDTO value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // @NotNull로 별도 처리
        }

        boolean exists = memberMissionRepository.existsByMemberIdAndMissionId(value.getMemberId(), value.getMissionId());

        if (exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("이미 도전 중인 미션입니다.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}

package org.example.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.spring.validation.validator.MissionChallengeValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionChallengeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMissionChallenge {
    String message() default "이미 도전 중인 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

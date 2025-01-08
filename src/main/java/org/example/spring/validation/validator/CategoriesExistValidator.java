package org.example.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.example.spring.exception.ErrorStatus;
import org.example.spring.repository.FoodCategoryRepository;
import org.example.spring.validation.annotation.ExistCategories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        if (values == null || values.isEmpty()) {
            return true; // 빈 값은 검증 통과 (필요 시 false로 변경 가능)
        }

        for (Long value : values) {
            if (!foodCategoryRepository.existsById(value)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("해당 카테고리 ID가 존재하지 않습니다: " + value)
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
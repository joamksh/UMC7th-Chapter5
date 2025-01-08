package org.example.spring.converter;

import org.example.spring.domain.FoodCategory;
import org.example.spring.domain.mapping.MemberPrefer;

import java.util.List;
import java.util.stream.Collectors;

public class MemberPreferConverter {
    public static List<MemberPrefer> toMemberPreferList(List<FoodCategory> foodCategories) {
        return foodCategories.stream()
                .map(foodCategory -> MemberPrefer.builder()
                        .foodCategory(foodCategory)
                        .build())
                .collect(Collectors.toList());
    }
}

package org.example.spring.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponseDTO {

    private Long id;
    private String title;
    private Float score;
    private String memberName;
    private String storeName;
}
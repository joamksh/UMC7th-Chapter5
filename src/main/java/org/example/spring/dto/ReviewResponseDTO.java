package org.example.spring.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponseDTO {

    private Long id;
    private String title;
    private Float score;
    private String body;
    private String memberName;
    private String storeName;
    private LocalDateTime createdAt;
}

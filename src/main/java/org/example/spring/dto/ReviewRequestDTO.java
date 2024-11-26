package org.example.spring.dto;

import jakarta.validation.constraints.*;

import lombok.Getter;

@Getter
public class ReviewRequestDTO {

    @NotNull
    private Long memberId;

    @NotNull
    private Long storeId;

    @NotBlank
    private String title;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private Float score;
}
package org.example.spring.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import org.example.spring.validation.annotation.ExistStore;

@Getter
public class ReviewRequestDTO {

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "가게 ID는 필수입니다.")
    @ExistStore(message = "존재하지 않는 가게입니다.")
    private Long storeId;

    @NotBlank(message = "리뷰 제목은 필수입니다.")
    private String title;

    @NotNull(message = "평점은 필수입니다.")
    @DecimalMin(value = "0.0", message = "평점은 0 이상이어야 합니다.")
    @DecimalMax(value = "5.0", message = "평점은 5 이하여야 합니다.")
    private Float score;
}

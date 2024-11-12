package umc.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewWithMemberAndStoreDto {
    private String nickname;
    private Float score;
    private String reviewBody;
    private String storeName;
    private LocalDateTime reviewDate;
}
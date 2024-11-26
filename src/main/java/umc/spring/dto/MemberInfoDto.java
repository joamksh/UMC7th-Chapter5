package umc.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberInfoDto {
    private String email;
    private String socialType;
    private int point;
    private String name;
}
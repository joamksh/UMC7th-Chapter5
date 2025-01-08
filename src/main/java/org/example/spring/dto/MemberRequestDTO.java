package org.example.spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.spring.domain.enums.Role;
import org.example.spring.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Setter
    public static class JoinDto {
        @NotBlank(message = "이름은 반드시 입력해야 합니다.")
        private String name;

        @NotNull(message = "성별은 반드시 선택해야 합니다.")
        private Integer gender;

        @NotNull(message = "출생년도는 반드시 입력해야 합니다.")
        private Integer birthYear;

        @NotNull(message = "출생월은 반드시 입력해야 합니다.")
        private Integer birthMonth;

        @NotNull(message = "출생일은 반드시 입력해야 합니다.")
        private Integer birthDay;

        @Size(min = 5, max = 12, message = "주소는 5자 이상 12자 이하로 입력해야 합니다.")
        private String address;

        @Size(min = 5, max = 12, message = "상세주소는 5자 이상 12자 이하로 입력해야 합니다.")
        private String specAddress;

        @ExistCategories(message = "선호 카테고리가 유효하지 않습니다.")
        private List<Long> preferCategory;

        @NotBlank(message = "이메일은 반드시 입력해야 합니다.")
        @Email(message = "유효한 이메일 형식을 입력해야 합니다.")
        private String email;

        @NotBlank(message = "비밀번호는 반드시 입력해야 합니다.")
        private String password;

        @NotNull(message = "역할은 반드시 선택해야 합니다.")
        private Role role;
    }
}

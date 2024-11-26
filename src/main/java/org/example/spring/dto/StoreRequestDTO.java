package org.example.spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class StoreRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    private Long regionId;
}
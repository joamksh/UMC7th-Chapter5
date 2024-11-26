package org.example.spring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring.dto.StoreRequestDTO;
import org.example.spring.dto.StoreResponseDTO;
import org.example.spring.response.ApiResponse;
import org.example.spring.service.StoreCommandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping
    public ApiResponse<StoreResponseDTO.StoreDetailDto> createStore(@RequestBody @Valid StoreRequestDTO requestDTO) {
        StoreResponseDTO.StoreDetailDto store = storeCommandService.createStore(requestDTO);
        return ApiResponse.onSuccess(store);
    }
}

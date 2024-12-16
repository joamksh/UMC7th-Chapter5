//package org.example.spring.controller;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.example.spring.converter.MemberConverter;
//import org.example.spring.domain.Member;
//import org.example.spring.dto.MemberRequestDTO;
//import org.example.spring.dto.MemberResponseDTO;
//import org.example.spring.response.ApiResponse;
//import org.example.spring.service.MemberCommandService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/members")
//public class MemberRestController {
//
//    private final MemberCommandService memberCommandService;
//
//    @PostMapping("/")
//    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
//        Member member = memberCommandService.joinMember(request);
//        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
//    }
//}

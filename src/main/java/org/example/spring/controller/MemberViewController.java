package org.example.spring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring.dto.MemberRequestDTO;
import org.example.spring.service.MemberCommandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberViewController {

    private final MemberCommandService memberCommandService;

    @GetMapping("/home")
    public String homePage(Model model) {
        return "home";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("memberJoinDto", new MemberRequestDTO.JoinDto());
        return "signup";
    }

    @PostMapping("/members/signup")
    public String joinMember(@ModelAttribute("memberJoinDto") @Valid MemberRequestDTO.JoinDto request,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error ->
                    System.out.println("Validation error on field: " + error.getField() + " - " + error.getDefaultMessage()));
            model.addAttribute("fieldErrors", bindingResult.getFieldErrors());
            return "signup";
        }

        // 입력값 디버깅
        System.out.println("회원가입 요청 데이터: " + request);

        try {
            memberCommandService.joinMember(request);
            System.out.println("회원가입 성공: " + request);
            return "redirect:/login";
        } catch (Exception e) {
            System.err.println("회원가입 중 오류 발생: " + e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

}

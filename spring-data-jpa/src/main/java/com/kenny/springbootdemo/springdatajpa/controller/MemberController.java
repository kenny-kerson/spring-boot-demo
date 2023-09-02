package com.kenny.springbootdemo.springdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public void registerMember() {
        memberService.registerMember();
    }

    @GetMapping("/member")
    public void inquiryMember() {
        memberService.inquiryMember();
    }

    @PostMapping("/member_history")
    public void saveMemberHistory() {
        memberService.saveMemberHistory();
    }
}

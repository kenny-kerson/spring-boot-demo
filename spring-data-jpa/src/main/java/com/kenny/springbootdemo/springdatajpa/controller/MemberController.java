package com.kenny.springbootdemo.springdatajpa.controller;

import com.kenny.springbootdemo.springdatajpa.domain.Member;
import com.kenny.springbootdemo.springdatajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/member")
    @Transactional
    public void registerMember() {
        registerMember1();
        memberService.registerMember2();
    }

    @Transactional
    public void registerMember1() {
        Member member1 = Member.builder()
                .id(1L)
                .name("홍길동")
                .build();

        memberRepository.save(member1);
    }


    @GetMapping("/member")
    public void inquiryMember() {
        memberRepository.findById(1L);
        memberRepository.findById(2L);
    }
}

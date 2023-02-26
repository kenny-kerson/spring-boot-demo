package com.kenny.springbootdemo.springdatajpa.controller;

import com.kenny.springbootdemo.springdatajpa.domain.Member;
import com.kenny.springbootdemo.springdatajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberServiceRequiresNew memberServiceRequiresNew;
    private final MemberRepository memberRepository;


    @Transactional
    public void registerMember() {
        Member member1 = Member.builder()
                .id(1L)
                .name("홍길동")
                .build();

        memberRepository.save(member1);
        memberServiceRequiresNew.registerMember2();
    }

    public void inquiryMember() {
        memberRepository.findById(1L);
        memberRepository.findById(2L);
    }
}

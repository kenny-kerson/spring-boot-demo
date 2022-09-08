package com.kenny.springbootdemo.springdatajpa.controller;

import com.kenny.springbootdemo.springdatajpa.domain.Member;
import com.kenny.springbootdemo.springdatajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceRequiresNew {

    private final MemberRepository memberRepository;

        @Transactional
//    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public void registerMember2() {
        Member member2 = Member.builder()
                .id(2L)
                .name("고길동")
                .build();

        memberRepository.save(member2);
    }
}

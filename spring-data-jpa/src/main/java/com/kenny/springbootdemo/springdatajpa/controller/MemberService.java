package com.kenny.springbootdemo.springdatajpa.controller;

import com.kenny.springbootdemo.springdatajpa.domain.Member;
import com.kenny.springbootdemo.springdatajpa.domain.MemberHistory;
import com.kenny.springbootdemo.springdatajpa.repository.MemberHistoryRepository;
import com.kenny.springbootdemo.springdatajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberServiceRequiresNew memberServiceRequiresNew;
    private final MemberRepository memberRepository;
    private final MemberHistoryRepository memberHistoryRepository;


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

    public void saveMemberHistory() {
        log.warn( "# save() 메서드 시작!!");
        memberHistoryRepository.save(
                MemberHistory.builder()
                        .memberId("test")
                        .contents("test 1변경이력")
                        .build()
        );
        log.warn( "# save() 메서드 종료!!");
    }
}

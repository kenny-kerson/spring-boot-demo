package com.kenny.testwas.card.domain;

import com.kenny.testwas.card.repository.카드기본Repository;
import com.kenny.testwas.card.repository.회원기본Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class 회원기본Test {

    @Autowired private 회원기본Repository memberRepository;
    @Autowired private 카드기본Repository cardRepository;

    @Test
    // TODO : Transactional으로 선언했을때와 그렇지 않을때의 쿼리수행이 다름 : 이에 대한 상세한 매커니즘 확인필요!!
    @Transactional
    void 회원기본_카드기본_레이지로딩_테스트() {

        final String memberNO = "850511";

        final 회원기본 member = 회원기본.builder()
                .회원번호(memberNO)
                .회원상태코드("정상")
                .회원명("고케니")
                .build();

        final 카드기본 card1 = 카드기본.builder()
                .카드번호("1111222233330001")
                .카드상태코드("정상")
                .회원번호(memberNO)
                .build();

        final 카드기본 card2 = 카드기본.builder()
                .카드번호("1111222233330002")
                .카드상태코드("정상")
                .회원번호(memberNO)
                .build();

        final 카드기본 card3 = 카드기본.builder()
                .카드번호("1111222233330003")
                .카드상태코드("정상")
                .회원번호(memberNO)
                .build();

        memberRepository.save(member);
        cardRepository.save(card1);
        cardRepository.save(card2);
        cardRepository.save(card3);

        memberRepository.flush();
        cardRepository.flush();

        final Optional<카드기본> byCard = cardRepository.findById("1111222233330001");
        System.out.println( byCard.get() );

        final 회원기본 byMember = byCard.get().get회원기본();
        System.out.println( byMember.get회원번호() );
    }
}
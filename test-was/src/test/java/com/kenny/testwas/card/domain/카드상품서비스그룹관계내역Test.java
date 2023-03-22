package com.kenny.testwas.card.domain;

import com.kenny.testwas.card.repository.카드상품서비스그룹관계내역Repository;
import com.kenny.testwas.card.repository.카드상품서비스기본Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class 카드상품서비스그룹관계내역Test {

    @Autowired 카드상품서비스그룹관계내역Repository relationRepository;
    @Autowired 카드상품서비스기본Repository baseRepository;
    @Autowired EntityManager entityManager;

    @BeforeEach
    void 초기데이터적재() {
        System.out.println( "__KENNY__ 초기데이터적재() START");

        final 카드상품서비스기본 svc1 = 카드상품서비스기본.builder()
                .카드상품서비스번호("S0001")
                .카드상품서비스명("샘플서비스1")
                .build();

        final 카드상품서비스기본 svc2 = 카드상품서비스기본.builder()
                .카드상품서비스번호("S0002")
                .카드상품서비스명("샘플서비스2")
                .build();

        final 카드상품서비스기본 svc3 = 카드상품서비스기본.builder()
                .카드상품서비스번호("S0003")
                .카드상품서비스명("샘플서비스3")
                .build();

        baseRepository.save(svc1);
        baseRepository.save(svc2);
        baseRepository.save(svc3);

        final 카드상품서비스그룹관계내역 group1 = 카드상품서비스그룹관계내역.builder()
                .카드상품코드("P0001")
                .카드상품서비스그룹번호("G0001")
                .카드상품서비스번호("S0001")
                .build();

        final 카드상품서비스그룹관계내역 group2 = 카드상품서비스그룹관계내역.builder()
                .카드상품코드("P0001")
                .카드상품서비스그룹번호("G0001")
                .카드상품서비스번호("S0002")
                .build();

        final 카드상품서비스그룹관계내역 group3 = 카드상품서비스그룹관계내역.builder()
                .카드상품코드("P0001")
                .카드상품서비스그룹번호("G0001")
                .카드상품서비스번호("S0003")
                .build();

        relationRepository.save(group1);
        relationRepository.save(group2);
        relationRepository.save(group3);
    }

    @Test
    @Transactional
    void LAZY_EAGER_쿼리테스트() {
        System.out.println( "__KENNY__ LAZY_EAGER_쿼리테스트() START");

        final Optional<카드상품서비스그룹관계내역> group = relationRepository.findById(new 카드상품서비스그룹관계내역PK("P0001", "G0001", "S0001"));
        System.out.println( "__KENNY__ group : " + group.get());
        System.out.println( "__KENNY__ : " + group.get().get카드상품서비스기본().get카드상품서비스명());
    }
}
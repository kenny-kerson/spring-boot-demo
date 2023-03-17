package com.kenny.testwas.card.repository;

import com.kenny.testwas.card.domain.카드상품서비스기본;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
class 카드상품서비스그룹선택내역QueryDslImplTest {

    @Autowired private 카드상품서비스그룹선택내역Repository repository;
    @Autowired private 카드상품서비스기본Repository repository2;

    @Test
    @Transactional
    void 단건조회_및_1차캐싱_확인() {
        repository2.save( 카드상품서비스기본.builder()
                .카드상품서비스번호("S0001")
                .build()
        );

        repository2.flush();

        System.out.println("__KENNY__ selectOne()");
        repository.selectOne();

        System.out.println("__KENNY__ findById()");
        repository2.findById("S0001");
    }

    @Test
    void 멀티조인() {
        repository.multiJoin()
                .forEach(System.out::println);
    }

    @Test
    void 서브쿼리() {
        repository.subQuery();
    }

    @Test
    void 페이징() {
        repository.paging();
    }
}
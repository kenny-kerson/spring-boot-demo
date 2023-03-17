package com.kenny.testwas.card.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class 카드상품서비스그룹선택내역QueryDslImplTest {

    @Autowired private 카드상품서비스그룹선택내역Repository repository;

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
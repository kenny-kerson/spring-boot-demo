package com.kenny.testwas.card.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class 카드상품서비스그룹선택내역QueryDslImplTest {

    @Autowired private 카드상품서비스그룹선택내역QueryDsl queryDsl;

    @Test
    void 멀티조인() {
        queryDsl.multiJoin()
                .forEach(System.out::println);
    }
}
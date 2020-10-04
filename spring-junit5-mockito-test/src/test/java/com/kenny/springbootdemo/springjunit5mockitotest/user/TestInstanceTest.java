package com.kenny.springbootdemo.springjunit5mockitotest.user;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

// 기본동작은 모든 메서드마다 인스턴스가 새로 생성됨 : 각 테스트간 의존성을 없애기 위해서
// 다만, 기능테스트나 시나리오테스트와 같이 순서가 있거나 공유해야 하는 데이터가 필요한 경우,
// JUnit5부터 테스트 인스턴스 생성모드를 변경할 수 있다.
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class TestInstanceTest {

    int cnt;

    @BeforeAll
    void beforeAll() {
        System.out.println( "__KENNY__ beforeAll : PER_CLASS일경우, static method로 선언할 필요가 없다!");
    }

    @Test
    void increaseCnt1() {
        System.out.println( "__KENNY__ cnt : " + cnt++);
    }

    @Test
    void increaseCnt2() {
        System.out.println( "__KENNY__ cnt : " + cnt++);
    }

    @AfterAll
    void afterAll() {
        System.out.println( "__KENNY__ afterAll : PER_CLASS일경우, static method로 선언할 필요가 없다!");
    }
}

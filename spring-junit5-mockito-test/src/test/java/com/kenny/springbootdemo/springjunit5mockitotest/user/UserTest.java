package com.kenny.springbootdemo.springjunit5mockitotest.user;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println( "__KENNY__ beforeAll()");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println( "__KENNY__ beforeEach()");
    }

    @Test
    @DisplayName("유저 만들기1")
    void createUser1() {
        User user = new User();
        assertThat(user).isNotNull();
    }

    @Test
    @DisplayName("유저 만들기2")
    @Disabled
    void createUser2() {
        User user = new User();
        assertThat(user).isNotNull();
    }

    @AfterEach
    void afterEach() {
        System.out.println( "__KENNY__ afterEach()");
    }

    @AfterAll
    static void afterAll() {
        System.out.println( "__KENNY__ beforeAll()");
    }

}
package com.kenny.springbootdemo.springjunit5mockitotest.user;

import org.assertj.core.api.Assumptions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.mockito.internal.matchers.Null;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.assertj.core.api.Assumptions.assumeThatCode;

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
    @DisplayName("유저 만들기1 😀")
    void createUser1() {
        User user = new User();
        assertThat(user).isNotNull();
    }

    @Test
    @DisplayName("유저 만들기2 🥰")
    @Disabled
    void createUser2() {
        User user = new User();
        assertThat(user).isNotNull();
    }

    @Test
    @DisplayName("Assertion : Null 체크")
    void assertionNullCheck() {
        final User user = null;

        assertThat(user).isNull();
    }

    @Test
    @DisplayName("Assertion : NotNull 체크")
    void assertionNotNullCheck() {
        final User user = User.builder()
                .id(1)
                .build();

        assertThat(user).isNotNull();
    }

    @Test
    @DisplayName("Assertion : True or False 체크")
    void assertionTrueOrFalseCheck() {
        final User user = User.builder()
                .id(1)
                .isMale(Boolean.TRUE)
                .build();

        assertThat(user.getIsMale())
                .as("True is Male")
                .isTrue();

    }

    @Test
    @DisplayName("Assertion : 동일여부 체크")
    void assertionEqualsCheck() {
        final User user = User.builder()
                .id(1)
                .build();

        assertThat(user.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("Assertion : 모든 Assertion 체크")
    void assertionAllCheck() {
        final String role = "USER";
        final String username = "kenny";

        final User user = User.builder()
                .id(1)
                .username(username)
                .password("1234")
                .roles(role)
                .isMale(Boolean.TRUE)
                .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(user.getId()).isEqualTo(1);
        softAssertions.assertThat(user.getUsername()).isEqualTo(username);
        softAssertions.assertThat(user.getIsMale()).isTrue();
        softAssertions.assertThat(user.getRoles()).isEqualTo(role);

        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Assertion : NullPointException 체크")
    void assertionNPECheck() {
        final User user = User.builder()
                .id(1)
                .build();

        assertThatNullPointerException()
                .isThrownBy(() -> user.getUsername().substring(0,3));
    }

    @Test
    @DisplayName("Assertion : Exception 체크")
    void assertionExceptionCheck() {
        final User user = User.builder()
                .id(1)
                .build();

        assertThatThrownBy(() -> user.getUsername().substring(0, 3))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Assertion : Timeout 체크")
    void assertionTimeoutCheck() {
        // assertj에는 Timeout 체크기능을 별도로 없는듯?!
    }

    @Test
    @DisplayName("Assumption : assumeThat()")
    void assumption1() {
        final String username = "kenny";
        final User user = User.builder()
                .id(1)
                .username(username)
                .roles("ADMIN")
                .password("1234")
                .isMale(Boolean.TRUE)
                .build();

        assumeThat(user.getIsMale()).isTrue();

        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getUsername()).isEqualTo(username);
    }

    @Test
    @DisplayName("Assumption : assumeThatCode()")
    void assumption2() {
        final String username = "kenny";
        final User user = User.builder()
                .id(1)
                .username(username)
                .build();

        assumeThatCode(() -> user.getPassword().substring(1,3))
                .isInstanceOf(NullPointerException.class);

        assumeThat(user.getUsername()).isEqualTo(username);
    }

    @Test
    @DisplayName("Enabled : EnabledOn")
    @EnabledOnOs(OS.MAC)
    void enabeld1() {
        System.out.println( "__KENNY__ EnabledOnOs");
    }

    @Test
    @DisplayName("Enabled : EnabledIf...")
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "TEST")
    void enabeld2() {
        System.out.println( "__KENNY__ EnabledIfEnvironmentVariable");
    }

    @Test
    @DisplayName("Disabled")
    @Disabled
    void disabled1() {
        System.out.println( "__KENNY__ Disabled1");
    }

    @Test
    @DisplayName("DisabledIfSystemProperty")
    @DisabledIfSystemProperty(named = "junit.system.env", matches = "1234")
    void disabled2() {
        System.out.println( "__KENNY__ Disabled2");
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
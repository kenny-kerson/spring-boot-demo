package com.kenny.springbootdemo.springjunit5mockitotest.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

public class UserRepeatedTest {

    @RepeatedTest(10)
    @DisplayName("테스트 반복하기 : @RepeatedTest (1)")
    void repeatedTest1() {
        System.out.println( "__KENNY__ repeatedTest1()");
    }

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} / {totalRepetitions}")
    @DisplayName("테스트 반복하기 : @RepeatedTest (2)")
    void repeatedTest2(RepetitionInfo repetitionInfo) {
        System.out.println( "__KENNY__ repetitionInfo : " + repetitionInfo.getCurrentRepetition() );
    }
}

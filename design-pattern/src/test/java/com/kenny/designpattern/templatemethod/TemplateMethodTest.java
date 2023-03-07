package com.kenny.designpattern.templatemethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemplateMethodTest {

    @Test
    void 템플릿메서드패턴_실행() {
        AbstractDisplay d1 = new CharDisplay('H');
        AbstractDisplay d2 = new StringDisplay("Hello, world.");

        // d1, d2의 구현체와 무관하게 템플릿메서드인 display()를 호출할 수 있다.
        // 구체적인 동작은 구현클래스의 메서드 구현에 따라 달라진다.
        // display()는 메서드 호출흐름과 동작만을 제어한다.
        d1.display();
        d2.display();
    }
}
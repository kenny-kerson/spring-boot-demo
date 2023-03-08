package com.kenny.designpattern.proxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProxyTest {

    @Test
    void 프록시패턴_실행() {
        // 클라이언트는 본인 클래스의 존재는 알지 못하고, 대리인(프록시) 클래스만 의존 & 사용한다!!!
        // 프록시 클래스를 본인 클래스로 대체해도 정확히 동작할 수 있다( = 프록시 클래스가 투과적이다 )
        Printable p = new PrinterProxy("Alice");
        // 대리인 클래스가 처리
        System.out.println("이름은 현재 " + p.getPrinterName() + "입니다.");

        p.setPrinterName("Bob");
        // 대리인 클래스가 처리
        System.out.println("이름은 현재 " + p.getPrinterName() + "입니다.");

        // 본인 클래스가 처리
        p.print("Hello, world");
    }
}
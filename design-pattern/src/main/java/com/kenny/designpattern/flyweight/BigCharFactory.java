package com.kenny.designpattern.flyweight;

import java.util.HashMap;
import java.util.Map;

// FlyweightFactory
public class BigCharFactory {

    // 한번 만들어진 BigChar 클래스는 pool에 저장한다.
    private Map<String, BigChar> pool = new HashMap<>();
    private static final BigCharFactory singleton = new BigCharFactory();

    private BigCharFactory() {}

    // 팩토리는 1개만 있어도 무방하므로, 싱글턴 패턴으로 생성한다.
    public static BigCharFactory getInstance() {
        return singleton;
    }

    // 동시성경합으로 동일한 문자에 대해 파일에 접근하는 오버헤드를 없애기 위해서, synchronized 키워드 사용!!
    public synchronized BigChar getBigChar(char charname) {
        BigChar bc = pool.get(charname);
        if (bc == null) {
            bc = new BigChar(charname);
            pool.put(String.valueOf(charname), bc);
        }
        return bc;
    }
}

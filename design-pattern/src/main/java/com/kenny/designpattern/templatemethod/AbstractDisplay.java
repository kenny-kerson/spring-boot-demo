package com.kenny.designpattern.templatemethod;

/*
 * 템플릿 메서드 패턴
 *    - 하위 클래스에서 구체적으로 처리한다.
 *    - 상속과 분할된 클래스간의 연관관계, 처리흐름을 가장 간단하게 이해하면서 근간이 되는 패턴!!!
 */
// AbstractClass
public abstract class AbstractDisplay {

    // 하위클래스에 구현을 맡기는 추상메서드!!!
    public abstract void open();
    public abstract void print();
    public abstract void close();

    // 템플릿 메서드 : 추상메서드를 직접 호출하면서 메서드들의 호출흐름과 동작을 제어한다!!
    public final void display() {
        open();
        for (int i = 0; i < 5; i++) {
            print();
        }
        close();
    }
}

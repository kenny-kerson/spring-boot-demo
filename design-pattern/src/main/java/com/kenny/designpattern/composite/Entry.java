package com.kenny.designpattern.composite;

// Component
public abstract class Entry {

    public abstract String getName();
    public abstract int getSize();
    public void printList() {
        printList("");
    }

    // prefix를 앞에 붙여서 목록을 표시한다( 하위클래스의 책임 )
    protected abstract void printList(String prefix);

    @Override
    public String toString() {
        // 템플릿 메서드 패턴과 유사하게, 하위클래스에서 구현하는 메서드들을 상위 클래스에서 호출한다
        return getName() + " (" + getSize() + ")";
    }
}

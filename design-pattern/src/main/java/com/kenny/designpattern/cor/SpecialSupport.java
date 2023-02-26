package com.kenny.designpattern.cor;

// ConcreteHandler
public class SpecialSupport extends Support {

    // 이 번호만 해결할 수 있는 하위 클래스
    private final int number;

    public SpecialSupport(final String name, final int number) {
        super(name);
        this.number = number;
    }

    @Override
    protected boolean resolve(final Trouble trouble) {
        return trouble.getNumber() == number;
    }
}

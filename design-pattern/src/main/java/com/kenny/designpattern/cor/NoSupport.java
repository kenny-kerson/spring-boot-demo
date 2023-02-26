package com.kenny.designpattern.cor;

public class NoSupport extends Support {
    public NoSupport(final String name) {
        super(name);
    }

    @Override
    protected boolean resolve(final Trouble trouble) {
        // 아무것도 해결하지 않는 하위클래스
        return false;
    }
}

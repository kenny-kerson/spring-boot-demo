package com.kenny.designpattern.cor;

public class LimitSupport extends Support {

    // 이 번호 미만이면 해결할 수 있는 하위클래스
    private final int limit;

    public LimitSupport(final String name, final int limit) {
        super(name);
        this.limit = limit;
    }

    @Override
    protected boolean resolve(final Trouble trouble) {
        return trouble.getNumber() < limit;
    }
}

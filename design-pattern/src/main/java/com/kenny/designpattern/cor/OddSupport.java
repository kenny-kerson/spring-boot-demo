package com.kenny.designpattern.cor;

public class OddSupport extends Support {
    public OddSupport(final String name) {
        super(name);
    }

    @Override
    protected boolean resolve(final Trouble trouble) {
        return trouble.getNumber() % 2 == 1;
    }
}

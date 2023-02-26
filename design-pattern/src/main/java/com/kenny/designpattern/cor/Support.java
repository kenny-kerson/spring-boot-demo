package com.kenny.designpattern.cor;

// Chain of Responsibility : 책임사슬 패턴
public abstract class Support {
    private String name;
    private Support next;

    public Support(final String name) {
        this.name = name;
        this.next = null;
    }

    // 떠넘길 곳을 정한다!!!
    public Support setNext(final Support next) {
        this.next = next;
        return this;
    }

    // 트러블 해결절차를 결정한다!!!
    public void support( Trouble trouble ) {
        // 해결하는 구현체는 이 클래스를 상속받은 하위클래스에서 진행한다!!!
        if ( resolve(trouble) ) {
            done( trouble );
        } else if ( next != null ) {
            next.support(trouble);
        } else {
            fail( trouble );
        }
    }

    // 해결하려고 한다!!! : 구체적인 구현은 subclass에 맡긴다

    protected abstract boolean resolve( Trouble trouble );
    protected void done(final Trouble trouble) {
        System.out.println( trouble + " is resolved by " + this + ".");
    }

    protected void fail(final Trouble trouble) {
        System.out.println( trouble + " cannot be resolved.");
    }
}

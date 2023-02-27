package com.kenny.designpattern.strategy;

import java.util.Random;

// ConcreteStrategy
public class WinningStrategy implements Strategy {

    private Random random;
    private boolean won = false;
    private Hand prevHand;

    public WinningStrategy( final int seed ) {
        this.random = new Random(seed);
    }

    @Override
    public Hand nextHand() {
        if (!won) {
            prevHand = Hand.getHand(random.nextInt(3));
        }

        return prevHand;
    }

    @Override
    public void study(final boolean win) {
        won = win;
    }
}

package com.kenny.designpattern.strategy;

// Strategy
public interface Strategy {
    Hand nextHand();
    void study(boolean win);
}

package com.kenny.designpattern.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrategyTest {

    @Test
    void 전략패턴_실행() {
        int seed1 = 1;
        int seed2 = 2;

        // 어떤 전략을 선택할지, Context 생성시에 전략클래스를 주입한다!!
        final Player player1 = new Player("KIM", new WinningStrategy(seed1));
        final Player player2 = new Player("LEE", new ProbStrategy(seed2));

        for (int i = 0; i < 10000; i++) {
            final Hand nextHand1 = player1.nextHand();
            final Hand nextHand2 = player2.nextHand();

            if (nextHand1.isStrongerThan(nextHand2)) {
                System.out.println("Winner:" + player1);
                player1.win();
                player2.lose();
            } else if (nextHand2.isStrongerThan(nextHand1)) {
                System.out.println("Winner:" + player2);
                player1.lose();
                player2.win();
            } else {
                System.out.println("Even...");
                player1.even();
                player2.even();
            }
        }

        System.out.println("Total Result:");
        System.out.println(player1);
        System.out.println(player2);
    }
}